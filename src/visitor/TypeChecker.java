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

    public TypeChecker (Program program) {
        VisitProgram(program);
        System.out.println("\n\nErros:\n"+errorLog.toString());
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

    boolean isPrimitiveType (TypeDenoter ty) {
        return ty instanceof PrimitiveType;
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
            error(type.id + " is not a declared type");
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
            error(arrayType + " is required to be ordinal");
        arrayType.elemTy = (TypeDenoter) arrayType.elemTy.accept(this);
        return arrayType;
    }

    @Override
    public Object VisitEnumeratedType(EnumeratedType type) {
        for (String c: type.newConstants)
            declareConstant(c, type);
        return type;
    }

    @Override
    public Object VisitSubRangeType(SubrangeType type) {
        Constant cH = type.high;
        if (cH instanceof IdConstant) {
            String nmH = ((IdConstant) cH).id;
            if ( !(env.get(nmH) instanceof Cons) )
                error(nmH + " must be constant");
        }

        Constant cL = type.low;
        if (cL instanceof IdConstant) {
            String nmL = ((IdConstant) cL).id;
            if ( !(env.get(nmL) instanceof Cons) )
                error(nmL + " must be constant");
        }

        TypeDenoter tyH = (TypeDenoter) cH.accept(this);
        TypeDenoter tyL = (TypeDenoter) cL.accept(this);
        if (isOrdinal(tyH) && tyH == tyL)
            return tyH;
        else {
            error("subrange limits must be ordinals and have the same type");
            return null;
        }
        //*** falta verificar que low � menor ou igual que high
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

    void declareConstant(String c, TypeDenoter type) {
        try {
            env.put(c, new Cons(type));
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
            error("operand of not must be boolean");
        return PrimitiveType.BOOLEAN;
    }

    @Override
    public Object VisitNumberLiteral(NumberLiteral exp) {
        return PrimitiveType.INTEGER;
    }

    @Override
    public Object VisitBinaryBooleanExpression(BinaryBooleanExpression exp) {
        //@TO-DO
        // Adaptar o pretty print para retornar Strings, utilizar nessas expressões
        if (exp.left.accept(this) != PrimitiveType.BOOLEAN && exp.right.accept(this) != PrimitiveType.BOOLEAN) {
            error("In: "+exp.toString()+" - left and right operands must result booleans.");
            return null;
        } else if (exp.left.accept(this) != PrimitiveType.BOOLEAN)
            error("In: "+exp.toString()+" - left operand must be boolean.");
        else if (exp.left.accept(this) != PrimitiveType.BOOLEAN)
            error("In: "+exp.toString()+" - operand must be boolean.");
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

        if (typeLeft == PrimitiveType.STRING || typeRight == PrimitiveType.STRING)
            return PrimitiveType.STRING;
        else {
            //@TO-DO
            // Adaptar o pretty print para retornar Strings, utilizar nessas expressões
            if (!isPrimitiveType(typeLeft)) error("In: " + exp.toString() + " - \"" + exp.left.toString() +
                    "\" must be a Primitive type");
            if (!isPrimitiveType(typeLeft)) error("In: " + exp.toString() + " - \"" + exp.right.toString() +
                    "\" must be a Primitive type");
            return PrimitiveType.INTEGER;
        }
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
        //@TO-DO
        // Adaptar o pretty print para retornar Strings, utilizar nessas expressões

        TypeDenoter typeLeft = (TypeDenoter) exp.left.accept(this),
                    typeRight = (TypeDenoter) exp.right.accept(this);
        if (!isOrdinal(typeLeft) && !isOrdinal(typeRight)) {
            error("In: " + exp.toString() + " - left and right operands must be Ordinals");
            return null;
        }
        if (!isOrdinal(typeLeft)) error("In: " + exp.toString() + " - left operand must be Ordinal");
        if (!isOrdinal(typeRight)) error("In: " + exp.toString() + " - right operand must be Ordinal");
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
        int limit = 0;
        if (function instanceof Function) {
            if (exp.actuals.size() > ((Function) function).parameters.size()) {
                error("In " + exp.toString() + ": the function is receiving more arguments than expected");
                limit = ((Function) function).parameters.size();
            } else if (exp.actuals.size() < ((Function) function).parameters.size()) {
                error("In " + exp.toString() + ": the function is receiving less arguments than expected");
                limit = exp.actuals.size();
            }
            for (int i = 0; i < limit; i++) {
                if (exp.actuals.get(i).accept(this) != ((Function) function).parameters.get(i))
                    error("In " + exp.toString() + ": The " + i + "º argument has a wrong type");
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
        TypeDenoter indexType = (TypeDenoter) exp.index.accept(this);

        if (!isOrdinal(indexType)) error ("In "+exp.toString()+": index expression must result an ordinal");

        // TODO
		/* 	var[index] est� bem tipado sse
		 * 	1. var est� bem tipada
		 *  2. index est� bem tipado
		 *  3. O tipo de var � array[t1] of ty2, para algum ty1,ty2.
		 *	4. index � do tipo ty1
		 *  O tipo retornado ao concluir a Visita � ty2.
		*/
        return exp.var.accept(this);
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