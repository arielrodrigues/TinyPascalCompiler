package visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import abstractSyntax.ConformantArray.*;
import abstractSyntax.Constant.*;
import abstractSyntax.Exp.*;
import abstractSyntax.FormalParameter.*;
import abstractSyntax.Operator.BinaryArithmeticOperator;
import abstractSyntax.Operator.BinaryBooleanOperator;
import abstractSyntax.Operator.RelationalOperator;
import abstractSyntax.Operator.Sign;
import abstractSyntax.labelsAndTypes.*;
import abstractSyntax.Node.*;
import abstractSyntax.procedureAndFunctionDeclaration.*;
import abstractSyntax.Stm.*;
import abstractSyntax.variablesDeclaration.*;

import semanticAnalyzer.Binding.*;
import utils.symbol.Exceptions.*;
import utils.symbol.*;

public class TypeChecker implements PascalVisitor {

    SymbolTable<Binding> env = new SymbolTable<>();
    public StringBuilder errorLog = new StringBuilder();
    Stack<List<Integer>> labelEnv = new Stack<>();
    PrettyPrint prettyPrint = new PrettyPrint();

    public TypeChecker (Program program) {
        VisitProgram(program);
        if (errorLog.length() > 0) {
            System.out.println("\n\nSemantic Errors:");
            for (String line : errorLog.toString().split("\n"))
                System.out.println("\t" + line);
            System.out.println("\n");
        } else System.out.println("No semantic erros found, go on\n");
    }

    private void error(String msg) {
        errorLog.append(msg+"\n");
    }

    boolean isOrdinal(TypeDenoter ty) {
        return ty == PrimitiveType.BOOLEAN ||
                ty == PrimitiveType.CHAR ||
                ty == PrimitiveType.INTEGER ||
                ty instanceof EnumeratedType;
    }

    private int getValue (Constant constant) {

        if (constant instanceof BooleanConstant)
            return ((BooleanConstant) constant).value? 1 : 0;
        if (constant instanceof CharacterConstant)
            return ((CharacterConstant) constant).value;
        if (constant instanceof IdConstant)
            return ((Cons) env.get(((IdConstant) constant).id)).value;
        if (constant instanceof SignedNumber)
            return ((SignedNumber) constant).sign == Sign.PLUS?
                    ((SignedNumber) constant).unsNum.value : -((SignedNumber) constant).unsNum.value;
        else // instance of UnsignedNumber
            return ((UnsignedNumber) constant).value;

    }

    List<Parameter> FormalsListHandler(List<FormalParameter> formals) {
        List<Parameter> parameters = new ArrayList<>();
        String name = "";
        Var var = null;
        try {
            for (FormalParameter formal : formals) {
                if (formal instanceof FormalPar) {
                    RefOrValue mechanism = ((FormalPar) formal).mechanism;
                    TypeDenoter type = (TypeDenoter) ((FormalPar) formal).type.accept(this);

                    parameters.add(new NormalParameter(mechanism, type));
                    name = ((FormalPar) formal).name;
                    var = new Var(type);
                } else if (formal instanceof ConformantArrayParameter) {
                    RefOrValue mechanism = ((ConformantArrayParameter) formal).mechanism;
                    Array type = (Array) formal.accept(this);

                    parameters.add(new ConformantParameter(mechanism, type));
                    name = ((ConformantArrayParameter) formal).name;
                    var = new Var(type);
                }
                if (var != null)
                    env.put(name, var);
            }
        } catch (AlreadyBoundException e) {
            error(name + e.log);
        }
        return parameters;
    }

    private void checkParameter (Binding bindingPar, TypeDenoter stmParType, int i, String name) {
        if (bindingPar instanceof NormalParameter) { // if is a normal parameter
            if (((NormalParameter) bindingPar).type instanceof Array) { // if parameter is an array
                if (!(stmParType instanceof Array))
                    error("In " + name + ": " + i + "º argument has a unexpected type");
                else {
                    if (((Array) stmParType).elemTy != ((Array) bindingPar).elemTy)
                        error("In " + name + ": " + i + "º argument has a unexpected type");
                    if (((Array) stmParType).range != ((Array) bindingPar).range)
                        error("In " + name + ": " + i + "º argument has a unexpected range type");
                }
            } else if (stmParType !=
                    ((NormalParameter) bindingPar).type.accept(this))
                error("In " + name + ": The " + i + "º argument has a unexpected type");
        } else if (bindingPar instanceof ConformantParameter) {
            if (!(stmParType instanceof Array))
                error("In " + name + ": " + i + "º argument has a unexpected type");
            else {
                if (((Array) stmParType).elemTy != ((ConformantParameter) bindingPar).type.elemTy)
                    error("In " + name + ": " + i + "º argument has a unexpected type");
                if (((Array) stmParType).range != ((ConformantParameter) bindingPar).type.range)
                    error("In " + name + ": " + i + "º argument has a unexpected range type");
            }
        }
    }


//********** Program

    @Override
    public Object VisitProgram(Program prog) {
        env.beginScope();
        prog.block.accept(this);
        try {
            env.endScope();
        } catch (InvalidLevelException e) {
            error(e.msg);
        }
        return null;
    }

    @Override
    public Object VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {
        return null;
    }

    @Override
    public Object VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {
        return null;
    }

    @Override
    public Object VisitRelationalOperator(RelationalOperator relationalOperator) {
        return null;
    }

    @Override
    public Object VisitSign(Sign sign) {
        return null;
    }


//********** Block

    @Override
    public Object VisitBlock(Block blck) {
        for (TypeDefinition tyDef: blck.typeDefs)
            tyDef.accept(this);
        for (VariableDeclaration varDec: blck.varDecs)
            varDec.accept(this);
        labelEnv.push(blck.labels);
        for (ProcedureOrFunctionDeclaration pfDec: blck.subprogs)
            pfDec.accept(this);
        blck.body.accept(this);
        labelEnv.pop();

        return null;
    }


//*********** Types
// Return the effective type of the Visited.

    @Override
    public Object VisitPrimitiveType(PrimitiveType type) {
        return type;
    }

    @Override
    public Object VisitTypeId(IdType type) {
        Binding ty = env.get(type.id);
        if (ty == null) {
            error(type.id + " was not a declared type");
            return null;
        }
        else if ( !(ty instanceof Type) ) {
            error(type.id + " is not a type");
            return null;
        } else
            return ((Type) ty).type;
    }

    @Override
    public Object VisitArray(Array arrayType) {
        arrayType.range = (TypeDenoter) arrayType.range.accept(this);
        if (!isOrdinal(arrayType.range))
            error("In " + arrayType.accept(prettyPrint) + ": "
                    + arrayType.range.accept(prettyPrint) + " is required to be ordinal");

        arrayType.elemTy = (TypeDenoter) arrayType.elemTy.accept(this);

        return arrayType;
    }

    @Override
    public Object VisitEnumeratedType(EnumeratedType type) {
        int i = 0;
        for (String c: type.newConstants)
            declareConstant(c, type, i++);
        return type;
    }

    @Override
    public Object VisitSubRangeType(SubrangeType type) {
        Constant cH = type.high;
        if (cH instanceof IdConstant) {
            String nmH = ((IdConstant) cH).id;
            if ( !(env.get(nmH) instanceof Cons) )
                error("In " +type.accept(prettyPrint) + ": " +nmH + " must be constant");
        }

        Constant cL = type.low;
        if (cL instanceof IdConstant) {
            String nmL = ((IdConstant) cL).id;
            if ( !(env.get(nmL) instanceof Cons) )
                error("In " +type.accept(prettyPrint) + ": " + nmL + " must be constant");
        }

        TypeDenoter tyH = (TypeDenoter) cH.accept(this);
        TypeDenoter tyL = (TypeDenoter) cL.accept(this);

        // test if lowConst < highConst
        if (getValue(type.low) >= getValue(type.high))
            error("In " + type.accept(prettyPrint) + ": low constant in subrange must be less than high constant");


        if (isOrdinal(tyH) && tyH == tyL)
            return tyH;
        else {
            error("In "+ type.accept(prettyPrint) +": subrange limits must be ordinals and have the same type");
            return null;
        }

    }


//********** TypeDefinition

    @Override
    public Object VisitTypeDefinition(TypeDefinition typeDef) {
        TypeDenoter ty = (TypeDenoter) typeDef.ty.accept(this);
        try {
            env.put(typeDef.id, new Type(ty));
        } catch (AlreadyBoundException e) {
            error(typeDef.id + e.log);
        }
        return null;
    }


//************ Constants
//Returns the type of the Visited constant

    @Override
    public Object VisitBooleanConstant(BooleanConstant cons) {
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public Object VisitCharacterConstant(CharacterConstant cons) {
        return PrimitiveType.CHAR;
    }

    @Override
    public Object VisitSignedNumber(SignedNumber cons) {
        return PrimitiveType.INTEGER;
    }

    @Override
    public Object VisitUnsignedNumber(UnsignedNumber cons) {
        return PrimitiveType.INTEGER;
    }

    @Override
    public Object VisitConstantId(IdConstant constantId) {
        return PrimitiveType.STRING;
    }


//*************** IdExpression Declaration

    void declareConstant(String c, TypeDenoter type, int value) {
        try {
            env.put(c, new Cons(type, value));
        } catch (AlreadyBoundException e) {
            error(c + e.log);
        }
    }

    @Override
    public Object VisitVariableDeclaration(VariableDeclaration varDec) {
        try {
            env.put(varDec.id, new Var((TypeDenoter) varDec.ty.accept(this)));
        } catch (AlreadyBoundException e) {
            error(varDec.id + e.log);
        }
        return null;
    }


//*********** Expressions

    @Override
    public Object VisitNotExpression(NotExpression exp) {
        TypeDenoter ty = (TypeDenoter) exp.exp.accept(this);
        if (ty != PrimitiveType.BOOLEAN)
            error("In " + exp.accept(prettyPrint) + ": operand of not must be boolean");
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public Object VisitNumberLiteral(NumberLiteral exp) {
        return PrimitiveType.INTEGER;
    }

    @Override
    public Object VisitBinaryBooleanExpression(BinaryBooleanExpression exp) {
        if (exp.left.accept(this) != PrimitiveType.BOOLEAN && exp.right.accept(this) != PrimitiveType.BOOLEAN) {
            error("In "+exp.accept(prettyPrint)+": left and right operands must be booleans");
            return null;
        } else if (exp.left.accept(this) != PrimitiveType.BOOLEAN)
            error("In "+exp.accept(prettyPrint)+": left operand must be boolean");
        else if (exp.right.accept(this) != PrimitiveType.BOOLEAN)
            error("In "+exp.accept(prettyPrint)+": right operand must be boolean");
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public Object VisitCharLiteral(CharLiteral charLiteral) {
        return PrimitiveType.CHAR;
    }

    @Override
    public Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression exp) {
        TypeDenoter typeLeft = (TypeDenoter) exp.left.accept(this);
        TypeDenoter typeRight = (TypeDenoter) exp.right.accept(this);

        if (typeLeft == PrimitiveType.STRING) {
            if (typeRight != null && (typeRight == PrimitiveType.BOOLEAN || typeRight == PrimitiveType.INTEGER))
                error("In " + exp.accept(prettyPrint) + ": " +
                        exp.right.accept(prettyPrint) + " must be character or string");
            return PrimitiveType.STRING;
        } else if (typeRight == PrimitiveType.STRING) {
            if (typeLeft != null && (typeLeft == PrimitiveType.BOOLEAN || typeLeft == PrimitiveType.INTEGER))
                error("In " + exp.accept(prettyPrint) + ": " +
                        exp.left.accept(prettyPrint) + " must be character or string");
            return PrimitiveType.STRING;
        }

        if (typeLeft == PrimitiveType.INTEGER) {
            if (typeRight != null && typeRight != PrimitiveType.INTEGER)
                error("In " + exp.accept(prettyPrint) + ": " +
                        exp.right.accept(prettyPrint) + " must be integer");
            return PrimitiveType.INTEGER;
        } else if (typeRight == PrimitiveType.INTEGER) {
            if (typeLeft != null && typeLeft != PrimitiveType.INTEGER)
                error("In " + exp.accept(prettyPrint) + ": " +
                        exp.left.accept(prettyPrint) + " must be integer");
            return PrimitiveType.INTEGER;
        }

        if (typeLeft != null || typeRight != null)
            error("In " + exp.accept(prettyPrint) + ": operands must be integer or string");

        return null;
    }

    @Override
    public Object VisitBooleanLiteral(BooleanLiteral exp) {
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public Object VisitCharacterLiteral(CharacterLiteral exp) {
        return PrimitiveType.CHAR;
    }

    @Override
    public Object VisitRelationalExpression(RelationalExpression exp) {

        TypeDenoter typeLeft = (TypeDenoter) exp.left.accept(this),
                typeRight = (TypeDenoter) exp.right.accept(this);

        if (typeLeft != null && typeRight != null && !isOrdinal(typeLeft) && !isOrdinal(typeRight)) {
            error("In " + exp.accept(prettyPrint) + ": left and right operands must be Ordinals");
            return null;
        }
        if (typeLeft != null && !isOrdinal(typeLeft))
            error("In " + exp.accept(prettyPrint) + ": left operand must be Ordinal");
        if (typeRight != null && !isOrdinal(typeRight))
            error("In " + exp.accept(prettyPrint) + ": right operand must be Ordinal");
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public Object VisitSignedExpression(SignedExpression exp) {
        return exp.exp.accept(this);
    }

    @Override
    public Object VisitStringLiteral(StringLiteral exp) {
        return PrimitiveType.STRING;
    }

    @Override
    public Object VisitFunctionDesignator(FunctionDesignator exp) {
        Binding function = env.get(exp.name);
        int limit = exp.actuals.size();

        if (function instanceof Function) {
            // check number of parameters
            if (exp.actuals.size() > ((Function) function).parameters.size()) {
                error("In " + exp.accept(prettyPrint) + ": the function is receiving more arguments than expected");
                limit = ((Function) function).parameters.size();
            } else if (exp.actuals.size() < ((Function) function).parameters.size()) {
                error("In " + exp.accept(prettyPrint) + ": the function is receiving less arguments than expected");
                limit = exp.actuals.size();
            }
            // check type of parameters
            for (int i = 0; i < limit; i++) {
                Binding bindingPar = ((Function) function).parameters.get(i);
                TypeDenoter expParType = (TypeDenoter) exp.actuals.get(i).accept(this);

                if (((Parameter) bindingPar).mechanism == RefOrValue.Ref &&
                        !((exp.actuals.get(i) instanceof IndexedVariable) || (exp.actuals.get(i) instanceof IdExpression)))
                    error("In " + exp.accept(prettyPrint) + ": " + i + "º argument can't be constant");

                checkParameter(bindingPar, expParType, i, (String) exp.accept(prettyPrint));
            }
            return ((Function) function).retTy;

        }
        error(exp.name + " is not declared as a function.");
        return null;
    }

    @Override
    public Object VisitIdExpression(IdExpression exp) {
        Binding bnd = env.get(exp.name);
        if (bnd == null)
            error("The variable " + exp.name + " is not declared");
        else if ( bnd instanceof Var)
            return ((Var) bnd).type;
        else if ( bnd instanceof Cons )
            return ((Cons) bnd).type;
        else if (bnd instanceof Function)
            return ((Function) bnd).retTy;
        else
            error(exp.name + " is not a variable");
        return null;
    }

    @Override
    public Object VisitIndexedVariable(IndexedVariable exp) {
        String varName = "";
        Binding bindingVar;

        if (exp.var instanceof IndexedVariable) {
            VariableAccess var = exp.var;
            TypeDenoter bindingType;

            while (var instanceof IndexedVariable) var = ((IndexedVariable) var).var; // loop to get variable name
            if (var instanceof IdExpression) varName = ((IdExpression) var).name;
            var = exp;
            bindingVar = env.get(varName);
            // if was not declared
            if (bindingVar == null) {
                error("In " + exp.accept(prettyPrint) + ": " + varName + " is not declared");
                return null;
            }

            // if was not declared as a variable
            if (!(bindingVar instanceof Var)) {
                error("In " + exp.accept(prettyPrint) + ": " + varName + " is not a variable");
                return null;
            }

            bindingType = ((Var) bindingVar).type;

            // if was not declared as an array
            if (!(bindingType instanceof Array)) {
                error("In " + exp.accept(prettyPrint) + ": " + varName + " is not an array");
                return null;
            }

            TypeDenoter ty;
            // check type of elements and indexes
            while (var instanceof IndexedVariable && bindingType instanceof Array) {
                ty = (TypeDenoter) ((Array) bindingType).range.accept(this);
                if (ty != ((IndexedVariable) var).index.accept(this) && ty != null) {
                    error("In " + exp.accept(prettyPrint) + ": " + ((IndexedVariable) var).index.accept(prettyPrint) + " has a unexpected type");
                    return ((Array) bindingType).elemTy.accept(this);
                }
                var = ((IndexedVariable) var).var;
                bindingType = ((Array) bindingType).elemTy;
            }
            if (var instanceof IndexedVariable)
                error("In " + exp.accept(prettyPrint) + ": trying to access a invalid position");

            return bindingType.accept(this);
        } else {
            varName = ((IdExpression) exp.var).name;
            bindingVar = env.get(varName);
            // if was not declared
            if (bindingVar == null) {
                error("In " + exp.accept(prettyPrint) + ": " + varName + " is not declared");
                return null;
            }

            // if was not declared as a variable
            if (!(bindingVar instanceof Var)) {
                error("In " + exp.accept(prettyPrint) + ": " + varName + " is not a variable");
                return null;
            }

            TypeDenoter bindingType = ((Var) bindingVar).type;

            // if was not declared as an array
            if (!(bindingType instanceof Array)) {
                error("In " + exp.accept(prettyPrint) + ": " + varName + " is not an array");
                return null;
            } else {
                TypeDenoter ty = (TypeDenoter) ((Array) bindingType).range;
                if (ty != exp.index.accept(this) && ty != null)
                    error("In " + exp.accept(prettyPrint) + ": " + exp.index.accept(prettyPrint) + " has a unexpected type");
            }
            return ((Array) bindingType).elemTy;
        }
    }


//********************** Formal Parameters

    @Override
    public Object VisitFormalPar(FormalPar formal) {
        return new NormalParameter(formal.mechanism, (TypeDenoter) formal.type.accept(this));
    }

    @Override
    public Object VisitConformantArrayParameter(ConformantArrayParameter conf) {
        return new ConformantParameter(conf.mechanism, (Array) conf.schema.accept(this));
    }


//******* Conformant Schemes

    @Override
    public Object VisitOneDimensionConformant(OneDimensionConformant sch) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object VisitMultiDimensionConformant(MultiDimensionConformant sch) {
        // TODO Auto-generated method stub
        return null;
    }

    //*************** Subprograms
    @Override
    public Object VisitProcedureDeclaration(ProcedureDeclaration dec) {
        try {
            env.beginScope();
            List<Parameter> parameters = FormalsListHandler(dec.formals);
            env.put(dec.nm, new Procedure(parameters));
            dec.body.accept(this);
            env.endScope();
            env.put(dec.nm, new Procedure(parameters));
        } catch (AlreadyBoundException e) {
            error(dec.nm + e.log);
        } catch (InvalidLevelException e) {
            error(e.msg);
        }
        return null;
    }

    @Override
    public Object VisitFunctionDeclaration(FunctionDeclaration dec) {
        try {
            env.beginScope();
            List<Parameter> parameters = FormalsListHandler(dec.formals);
            env.put(dec.nm, new Function(parameters, dec.resultTy));
            dec.body.accept(this);
            env.endScope();
            env.put(dec.nm, new Function(parameters, dec.resultTy));
        } catch (AlreadyBoundException e) {
            error(dec.nm + e.log);
        } catch (InvalidLevelException e) {
            error(e.msg);
        }
        return null;
    }


    //*************** Statements
    @Override
    public Object VisitEmptyStm(EmptyStatement stm) {
        return null;
    }

    @Override
    public Object VisitAssignStm(AssignmentStatement assignStm) {
        if (assignStm.left.accept(this) instanceof semanticAnalyzer.Binding.Cons)
            error("In "+assignStm.accept(prettyPrint)+": constant value can't be modified");
        else {
            TypeDenoter ty = (TypeDenoter) assignStm.right.accept(this);
            TypeDenoter ty1 = (TypeDenoter) assignStm.left.accept(this);
            if (assignStm.left instanceof IdExpression || assignStm.left instanceof IndexedVariable) {
                if (assignStm.left.accept(this) != null && assignStm.left.accept(this) != ty) {
                    error("In "+assignStm.accept(prettyPrint)+": "+assignStm.right.accept(prettyPrint)+" has an unexpected type");
                    if (!(assignStm.left instanceof IdExpression)) {
                        VariableAccess stm = assignStm.left;
                        while (!(stm instanceof IdExpression))
                            stm = ((IndexedVariable) stm).var;
                        return env.get(((IdExpression) stm).name);
                    }
                    return env.get(((IdExpression) assignStm.left).name);
                }
            } else error("In "+assignStm.accept(prettyPrint)+": "+assignStm.left.accept(prettyPrint)+" can't be modified");
        }
        return null;
    }

    @Override
    public Object VisitGotoStatement(GotoStatement stm) {
        if (!labelEnv.contains(stm.label))
            error("In "+stm.accept(prettyPrint)+": label "+prettyPrint.VisitUnsignedNumber(stm.label)+" was not declared");
        return null;
    }

    @Override
    public Object VisitProcedureStm(ProcedureStatement stm) {
        Binding procedure = env.get(stm.name);
        int limit = stm.actuals.size();

        if (procedure instanceof Procedure) {
            // check number of parameters
            if (stm.actuals.size() > ((Procedure) procedure).parameters.size()) {
                error("In " + stm.accept(prettyPrint) + ": the procedure is receiving more arguments than expected");
                limit = ((Procedure) procedure).parameters.size();
            } else if (stm.actuals.size() < ((Procedure) procedure).parameters.size()) {
                error("In " + stm.accept(prettyPrint) + ": the procedure is receiving less arguments than expected");
                limit = stm.actuals.size();
            }
            // check type of parameters
            for (int i = 0; i < limit; i++) {
                Binding bindingPar = ((Procedure) procedure).parameters.get(i);
                TypeDenoter stmParType = (TypeDenoter) stm.actuals.get(i).accept(this);

                if (((Parameter) bindingPar).mechanism == RefOrValue.Ref &&
                        !((stm.actuals.get(i) instanceof IndexedVariable) || (stm.actuals.get(i) instanceof IdExpression)))
                    error("In " + stm.accept(prettyPrint) + ": " + i + "º argument can't be constant");

                checkParameter(bindingPar, stmParType, i, (String) stm.accept(prettyPrint));
            }
        } else if (procedure instanceof Function)
            error ("In " + stm.accept(prettyPrint) + ": returned value by a function must be used");
        return null;
    }

    @Override
    public Object VisitCompStm(CompoundStatement compStm) {
        for (Statement stm : compStm.stmts) stm.accept(this);
        return null;
    }

    @Override
    public Object VisitIfStm(IfStatement stm) {
        if (stm.condition.accept(this) != PrimitiveType.BOOLEAN)
            error("In "+stm.condition.accept(prettyPrint)+": if condition must be boolean");
        stm.thenPart.accept(this);
        if (stm.elsePart != null) stm.elsePart.accept(this);
        return null;
    }

    @Override
    public Object VisitLabeledStm(LabeledStatement lblStm) {
        lblStm.stm.accept(this);
        return null;
    }

    @Override
    public Object VisitWhileStm(WhileStatement stm) {
        if (stm.condition.accept(this) != PrimitiveType.BOOLEAN)
            error("In "+stm.condition.accept(prettyPrint)+": while condition must be boolean");
        stm.body.accept(this);
        return null;
    }

}