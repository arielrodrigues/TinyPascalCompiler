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
import semanticAnalyzer.symbol.*;
import semanticAnalyzer.symbol.Exceptions.*;

public class TypeChecker implements PascalVisitor {

    SymbolTable<Binding> env = new SymbolTable<>();
    StringBuilder errorLog = new StringBuilder();
    Stack<List<Integer>> labelEnv = new Stack<>();
    static PrettyPrint prettyPrint = new PrettyPrint();

    public TypeChecker (Program program) {
        VisitProgram(program);
        System.out.println("\n\nSemantic Errors:\n"+errorLog.toString());
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
            if (exp.actuals.size() > ((Function) function).parameters.size()) {
                error("In " + exp.accept(prettyPrint) + ": the function is receiving more arguments than expected");
                limit = ((Function) function).parameters.size();
            } else if (exp.actuals.size() < ((Function) function).parameters.size()) {
                error("In " + exp.accept(prettyPrint) + ": the function is receiving less arguments than expected");
                limit = exp.actuals.size();
            }
            for (int i = 0; i < limit; i++) {
                if (((Function) function).parameters.get(i) instanceof NormalParameter)
                    if (exp.actuals.get(i).accept(this) !=
                            ((NormalParameter) ((Function) function).parameters.get(i)).type.accept(this))
                            error("In " + exp.accept(prettyPrint) + ": The " + i + "º argument has a wrong type");
                else if (((Function) function).parameters.get(i) instanceof ConformantParameter);
                        /*if (exp.actuals.get(i).accept(this) !=
                                ((ConformantParameter) ((Function) function).parameters.get(i)).type.accept(this))
                            error("In " + exp.accept(prettyPrint) + ": The " + i + "º argument has a wrong type");*/
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
        TypeDenoter varType = (TypeDenoter) exp.var.accept(this);
        TypeDenoter indexType = (TypeDenoter) exp.index.accept(this);

        if (varType == null) return null;
        if (!(varType instanceof Array)) {
            error("In " + exp.accept(prettyPrint) + ": " + exp.var.accept(prettyPrint) + " must be an array");
            return null;
        }

        if (!isOrdinal(indexType))
            error ("In " + exp.toString() + ": " + exp.index.accept(prettyPrint) + "expression must be ordinal");

        if (exp.var instanceof IdExpression) {
            String varName = ((IdExpression) exp.var).name;
            Binding var = env.get(varName);

            if (var instanceof Var) {
                if (((Var) var).type instanceof Array)
                if (indexType != ((Array) ((Var) var).type).range)
                    error("In " + exp.toString() + ": "+exp.index.accept(prettyPrint)+" has unexpected type");

                if (((Array) varType).range instanceof SubrangeType) {
                    int indexedLowValue = getValue(((SubrangeType) ((Array) varType).range).low),
                        bindingLowValue = getValue(((SubrangeType) (((Array) ((Var) var).type).range)).low),
                        indexedHighValue = getValue(((SubrangeType) ((Array) varType).range).high),
                        bindingHighValue = getValue(((SubrangeType) (((Array) ((Var) var).type).range)).high);

                    if (indexedLowValue < bindingLowValue || indexedHighValue > bindingHighValue)
                        error("In " + exp.accept(prettyPrint) + ": " + exp.index.accept(prettyPrint) + " is an invalid value");
                }
                return ((Var) var).type;
            } else {
                error("In " + exp.accept(prettyPrint) + ": " + ((IdExpression) exp.var).name + " is not a variable");
                return null;
            }
        } else
            error("In " + exp.accept(prettyPrint) + ": " + ((IdExpression) exp.var).name + " is not a variable");

        return null;
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
            error("You cant change the value of a constant");
        else {
            TypeDenoter ty = (TypeDenoter) assignStm.right.accept(this);
            if (assignStm.left instanceof IdExpression) {
                if (assignStm.left.accept(this) != null && assignStm.left.accept(this) != ty) {
                    error("you cant assign expressions MELHORE ESSE TEXTO!!!!!");
                    return env.get(((IdExpression) assignStm.left).name);
                }
            } else error("The right side isn't a variable.");
        }
        return null;
    }

    @Override
    public Object VisitGotoStatement(GotoStatement stm) {
        if (!labelEnv.contains(stm.label))
            error("Label "+prettyPrint.VisitUnsignedNumber(stm.label)+" was not declared");
        return null;
    }

    @Override
    public Object VisitProcedureStm(ProcedureStatement stm) {
        Binding procedure = env.get(stm.name);
        int limit = 0;

        if (procedure instanceof Procedure) {
            if (stm.actuals.size() > ((Procedure) procedure).parameters.size()) {
                error("In " + stm.toString() + ": the procedure is receiving more arguments than expected");
                limit = ((Procedure) procedure).parameters.size();
            } else if (stm.actuals.size() < ((Procedure) procedure).parameters.size()) {
                error("In " + stm.toString() + ": the procedure is receiving less arguments than expected");
                limit = stm.actuals.size();
            }
            for (int i = 0; i < limit; i++) {
                if (stm.actuals.get(i).accept(this) != ((Procedure) procedure).parameters.get(i))
                    error("In " + stm.toString() + ": The " + i + "º argument has a wrong type");
            }
        } else error(stm.name + " is not declared as a procedure.");

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
            error("If condition must be boolean");
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
            error("While condition must be boolean");
        stm.body.accept(this);
        return null;
    }

}