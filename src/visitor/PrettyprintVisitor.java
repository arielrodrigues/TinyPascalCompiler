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
import org.codehaus.plexus.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class PrettyprintVisitor implements PascalVisitor {

    private int indentLevel = 0;

    private void indent() { indentLevel++; }
    private void unindent() { indentLevel--; }

    private void print (Object str, boolean indent) {
        String tabs = indent? StringUtils.repeat("\t", indentLevel): "";
        System.out.print(tabs + str);
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
        ???????
    }

    @Override
    public void VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {
        ??????
    }

    @Override
    public void VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {
        ??????
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
        print(unsignedNumber, false);
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
    public void VisitIndexedVariable(IndexedVariable indexedVariable) {
        indexedVariable.var.accept(this);
        print(" = ", false);
        indexedVariable.index.accept(this);
    }

    @Override
    public void VisitNotExpression(NotExpression notExpression) {
        ???????
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
    public void VisitFormalRef(FormalRef formalRef) {
        ??????
    }

    @Override
    public void VisitFormalVar(FormalVar formalVar) {
        ??????
    }

    @Override
    public void VisitRefOrValue(RefOrValue refOrValue) {
        ??????
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
        print(typeDefinition.id + " : ", false);
        typeDefinition.ty.accept(this);
        print("; ", false);
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
            for (UnsignedNumber uNum : block.labels.subList(1, block.labels.size())) {
                uNum.accept(this); print(", ", false);
            }
            unindent();
        }
        if (!block.typeDefs.isEmpty()) {
            print("TYPE\n", true);
            indent();
            print("", true);
            for (TypeDefinition typeDef : block.typeDefs) typeDef.accept(this);
            unindent();
        }
        if (!block.varDecs.isEmpty()) {
            print ("VAR\n", true);
            indent();
            print("", true);
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
        block.body.accept(this);
    }

    @Override
    public void VisitProgram(Program program) {
        System.out.print("Program " + program.id + "(");
        for (String id : program.io) System.out.print(id + ", ");
        System.out.print(")");
        program.block.accept(this);
    }

    /* visit Operators */
    @Override
    public void VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {

    }

    @Override
    public void VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {

    }

    @Override
    public void VisitRelationalOperator(RelationalOperator relationalOperator) {

    }

    @Override
    public void VisitSign(Sign sign) {
        char symbolSign = (sign == Sign.PLUS)? '+' : '-';
        print(symbolSign, false);
    }

    /* visit Procedures and functions declarations */
    @Override
    public void VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {

    }

    @Override
    public void VisitFunctionDesignator(FunctionDesignator functionDesignator) {

    }

    @Override
    public void VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {

    }

    /* visit Statements */
    @Override
    public void VisitAssignStm(AssignmentStatement assignStm) {
        //assignStm.left.print(this);
        System.out.println(" + ");
        //assignStm.right.print(this);
        System.out.println("");
    }

    @Override
    public void VisitCompStm(CompoundStatement compStm) {
        for (Statement stm : compStm.stmts) {
            //stm.print();
        }
    }

    @Override
    public void VisitEmptyStm(EmptyStatement eStm) {
        System.out.println("");
    }

    @Override
    public void VisitGotoStatement(GotoStatement gotoStatement) {

    }

    @Override
    public void VisitIfStm(IfStatement ifStm) {
        // colocar uns testes p add begin end e parenteses
        System.out.print("if ");
        //ifStm.condition.print(this);
        System.out.print(" then ");
        //ifStm.thenPart.print(this);
        System.out.print(" else ");
        //ifStm.elsePart.print(this);
        System.out.print('\n');
    }

    @Override
    public void VisitProcedureStm(ProcedureDeclaration procedureStm) {
        System.out.print("Procedure ");
        //procedureStm.nm.print(this);
        System.out.print("(");
        for (FormalParameter fPar: procedureStm.formals) {
            //fPar.print(this);
            System.out.print(", ");
        }
        System.out.println(")\nbegin");
        //procedureStm.body.print(this);
        System.out.println("end;");
    }

    @Override
    public void VisitWhileStm(WhileStatement whileStm) {
        System.out.print("while ");
        //whileStm.condition.print(this);
        System.out.println("\nbegin");
        //whileStm.body.visit();
        System.out.println("end");
    }

    /* visit Variables Declarations */
    @Override
    public void VisitVariable(Variable variable) {

    }

    @Override
    public void VisitVariableDeclaration(VariableDeclaration variableDeclaration) {
        print(variableDeclaration.id + " : ", false);
        variableDeclaration.ty.accept(this);
        print("; ", false);
    }
}
