package visitor;

import abstractSyntax.ConformantArray.*;
import abstractSyntax.Constant.*;
import abstractSyntax.Exp.*;
import abstractSyntax.FormalParameter.*;
import abstractSyntax.labelsAndTypes.*;
import abstractSyntax.Node.*;
import abstractSyntax.Operator.*;
import abstractSyntax.procedureAndFunctionDeclaration.*;
import abstractSyntax.Stm.*;
import abstractSyntax.variablesDeclaration.*;


import java.util.Collections;
import java.util.List;

public class PrettyprintVisitor implements PascalVisitor {

    private int indentLevel = 0;

    private void indent() { indentLevel++; }
    private void unindent() { --indentLevel; }

    private void print (Object str, boolean indent) {
        String tabs = indent? String.join("", Collections.nCopies(indentLevel, "    ")): "";
        System.out.print(tabs + str);
    }

    public PrettyprintVisitor (Program program) {
        VisitProgram(program);
    }

    private void PrettyPrintList (List<String> list, boolean parentheses) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (Object item : list.subList(0, list.size())) sb.append(", " + item);
        if (parentheses) {sb.insert(0, '('); sb.append(')');}
        print(sb.toString(), false);
    }

    /* visit Conformant Array */
    @Override
    public void VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter) {
        //???????
    }

    @Override
    public void VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {
        //??????
    }

    @Override
    public void VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {
        //??????
    }

    @Override
    public void VisitBooleanConstant(BooleanConstant booleanConstant) {

    }

    @Override
    public void VisitCharacterConstant(CharacterConstant characterConstant) {

    }

    /* visit Constants */
    @Override
    public void VisitCharacterLiteral(CharacterLiteral characterLiteral) {
        print(characterLiteral.value, false);
    }

    @Override
    public void VisitSignedNumber(SignedNumber signedNumber) {
        signedNumber.sign.accept(this);
        signedNumber.unsNum.accept(this);
    }

    @Override
    public void VisitUnsignedNumber(UnsignedNumber unsignedNumber) {
        print(unsignedNumber.value, false);
    }

    /* visit Expressions */
    @Override
    public void VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {
        binaryArithmeticExpression.left.accept(this);
        binaryArithmeticExpression.op.accept(this);
        binaryArithmeticExpression.right.accept(this);
    }

    @Override
    public void VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression) {
        binaryBooleanExpression.left.accept(this);
        binaryBooleanExpression.op.accept(this);
        binaryBooleanExpression.right.accept(this);
    }

    @Override
    public void VisitCharLiteral(CharLiteral charLiteral) {
        print(charLiteral.value, false);
    }

    @Override
    public void VisitBooleanLiteral(BooleanLiteral booleanLiteral) {

    }

    @Override
    public void VisitIndexedVariable(IndexedVariable indexedVariable) {
        indexedVariable.var.accept(this);
        print(" = ", false);
        indexedVariable.index.accept(this);
    }

    @Override
    public void VisitNotExpression(NotExpression notExpression) {
        //???????
    }

    @Override
    public void VisitNumberLiteral(NumberLiteral numberLiteral) {
        print(numberLiteral.value, false);
    }

    @Override
    public void VisitRelationalExpression(RelationalExpression relationalExpression) {
        relationalExpression.left.accept(this);
        relationalExpression.op.accept(this);
        relationalExpression.right.accept(this);
    }

    @Override
    public void VisitSignedExpression(SignedExpression signedExpression) {
        signedExpression.sign.accept(this);
        signedExpression.exp.accept(this);
    }

    @Override
    public void VisitStringLiteral(StringLiteral stringLiteral) {
        print(stringLiteral.value, false);
    }

    /* visit Formal Parameters */

    @Override
    public void visitFormalPar(FormalPar formalPar) {
        if (formalPar.mechanism == RefOrValue.Ref) VisitFormalRef(formalPar);
        else if (formalPar.mechanism == RefOrValue.Val) VisitFormalVar(formalPar);
    }

    @Override
    public void VisitFormalRef(FormalPar formalRef) {
        print(formalRef.name+" : ", false);
        formalRef.type.accept(this);
    }

    @Override
    public void VisitFormalVar(FormalPar formalVar) {
        print("Var "+formalVar.name+" : ", false);
        formalVar.type.accept(this);
    }

    /* visit Labels and Types */
    @Override
    public void VisitArray(Array array) {
        print("ARRAY", true);
        array.range.accept(this);
        print(" of ", false);
        array.elemTy.accept(this);
    }

    @Override
    public void VisitEnumeratedType(EnumeratedType enumeratedType) {
        PrettyPrintList(enumeratedType.newConstants, true);
    }

    @Override
    public void VisitPrimitiveType(PrimitiveType primitiveType) {
        if (primitiveType.INTEGER != null) print("integer", false);
        else if (primitiveType.CHAR != null) print("char", false);
        else if (primitiveType.STRING != null) print("string", false);
        else if (primitiveType.BOOLEAN != null) print("boolean", false);
    }

    @Override
    public void VisitSubRangeType(SubrangeType subrangeType) {
        subrangeType.low.accept(this);
        print("..", false);
        subrangeType.high.accept(this);
    }

    @Override
    public void VisitTypeDefinition(TypeDefinition typeDefinition) {
        print(typeDefinition.id + " : ", true);
        typeDefinition.ty.accept(this);
        print(";\n", false);
    }

    @Override
    public void VisitTypeId(TypeId typeId) {
        print(typeId.id, false);
    }

    /* visit Nodes */
    @Override
    public void VisitBlock(Block block) {
        indent();
        if (!block.labels.isEmpty()) {
            print("LABEL\n", true);
            indent();
            print(block.labels.get(0), true);
            for (Integer uNum : block.labels.subList(1, block.labels.size())) {
                print(uNum + ", ", false);
            }
            unindent();
        }
        if (!block.typeDefs.isEmpty()) {
            print("TYPE\n", true);
            indent();
            for (TypeDefinition typeDef : block.typeDefs) typeDef.accept(this);
            unindent();
        }
        if (!block.varDecs.isEmpty()) {
            print ("VAR\n", true);
            indent();
            for (VariableDeclaration varDec : block.varDecs) varDec.accept(this);
            unindent();
        }
        if (!block.subprogs.isEmpty()) {
            indent();
            for (ProcedureOrFunctionDeclaration subprog : block.subprogs) {
                print("", true);
                subprog.accept(this);
            }
            unindent();
        }
        print("begin", true);
        indent();
        block.body.accept(this);
        unindent();
        print("end", true);
    }

    @Override
    public void VisitIndexType(IndexType index) {

    }

    @Override
    public void VisitProgram(Program program) {
        System.out.print("Program " + program.id + "(");
        for (String id : program.io) System.out.print(id + ", ");
        System.out.print(");\n");
        program.block.accept(this);
        print(".", false);
    }

    /* visit Operators */
    @Override
    public void VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {
        switch (binaryArithmeticOperator.hashCode()) {
            case 0: print("DIV", false); break;
            case 1: print("MOD", false); break;
            case 2: print("PLUS", false); break;
            case 3: print("TIMES", false); break;
            case 4: print("MINUS", false); break;
        }
    }

    @Override
    public void VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {
        switch (binaryBooleanOperator.hashCode()) {
            case 0: print("AND", false); break;
            case 1: print("OR", false); break;
        }
    }

    @Override
    public void VisitRelationalOperator(RelationalOperator relationalOperator) {
        switch (relationalOperator.hashCode()) {
            case 0: print("EQ", false); break;
            case 1: print("NEQ", false); break;
            case 2: print("LT", false); break;
            case 3: print("GT", false); break;
            case 4: print("LTE", false); break;
            case 5: print("GTE", false); break;
        }
    }

    @Override
    public void VisitSign(Sign sign) {
        char signSymbol = (sign.hashCode() == 0)? '+' : '-';
        print(signSymbol, false);
    }

    /* visit Procedures and functions declarations */
    @Override
    public void VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        print("Function "+functionDeclaration.nm+" (", true);
        for (FormalParameter formal : functionDeclaration.formals) {
            if (formal instanceof FormalPar) {
                FormalPar par = (FormalPar) formal;
                par.accept(this);
            }
        }
        print(") : ", false);
        functionDeclaration.resultTy.accept(this); print (';', false);
        functionDeclaration.body.accept(this); print (';', false);
    }

    @Override
    public void VisitFunctionDesignator(FunctionDesignator functionDesignator) {
        print(functionDesignator.name + "(", true);
        for (Expression exp : functionDesignator.actuals) exp.accept(this);
        print (")", false);
    }

    @Override
    public void VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        print("Procedure "+procedureDeclaration.nm+" (", true);
        for (FormalParameter formal : procedureDeclaration.formals) formal.accept(this);
        print(") : ", false);
        procedureDeclaration.body.accept(this); print (';', false);
    }

    /* visit Statements */
    @Override
    public void VisitAssignStm(AssignmentStatement assignStm) {
        print("", true);
        assignStm.left.accept(this);
        System.out.println(" + ");
        assignStm.right.accept(this);
        print(";\n", false);
    }

    @Override
    public void VisitCompStm(CompoundStatement compStm) {
        for (Statement stm : compStm.stmts) stm.accept(this);
    }

    @Override
    public void VisitEmptyStm(EmptyStatement eStm) {
        print("", false);
    }

    @Override
    public void VisitGotoStatement(GotoStatement gotoStatement) {
        print(gotoStatement.label+": ", true);
    }

    @Override
    public void VisitIfStm(IfStatement ifStm) {
        print("if ", true);
        ifStm.condition.accept(this);
        print(" then", false);
        ifStm.thenPart.accept(this); print("\n", false);
        print("else", true);
        ifStm.elsePart.accept(this);
        print("\n", false);
    }

    @Override
    public void VisitProcedureStm(ProcedureStatement procedureStm) {
        print(procedureStm.name+" (", true);
        for (Expression exp : procedureStm.actuals) exp.accept(this);
        print(");\n", false);
    }

    @Override
    public void VisitWhileStm(WhileStatement whileStm) {
        System.out.print("while ");
        whileStm.condition.accept(this);
        whileStm.body.accept(this);
    }

    /* visit Variables Declarations */
    @Override
    public void VisitVariable(Variable variable) {
        print(variable.name, false);
    }

    @Override
    public void VisitVariableDeclaration(VariableDeclaration variableDeclaration) {
        print(variableDeclaration.id + " : ", true);
        variableDeclaration.ty.accept(this);
        print(";\n", false);
    }
}