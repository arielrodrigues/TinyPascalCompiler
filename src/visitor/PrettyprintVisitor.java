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

public class PrettyprintVisitor implements PasVisitor {

    /* visit Conformant Array */
    @Override
    public void visitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter) {

    }

    @Override
    public void visitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {

    }

    @Override
    public void visitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {

    }

    /* visit Constants */
    @Override
    public void visitCharacterLiteral(CharacterLiteral characterLiteral) {
        System.out.print(characterLiteral.value);
    }

    @Override
    public void visitSignedNumber(SignedNumber signedNumber) {
        signedNumber.accept(this);
    }

    @Override
    public void visitUnsignedNumber(UnsignedNumber unsignedNumber) {

    }

    /* visit Expressions */
    @Override
    public void visitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {

    }

    @Override
    public void visitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression) {

    }

    @Override
    public void visitCharLiteral(CharLiteral charLiteral) {

    }

    @Override
    public void visitIndexedVariable(IndexedVariable indexedVariable) {

    }

    @Override
    public void visitNotExpression(NotExpression notExpression) {

    }

    @Override
    public void visitNumberLiteral(NumberLiteral numberLiteral) {

    }

    @Override
    public void visitRelationalExpression(RelationalExpression relationalExpression) {

    }

    @Override
    public void visitSignedExpression(SignedExpression signedExpression) {

    }

    @Override
    public void visitStringLiteral(StringLiteral stringLiteral) {

    }

    /* visit Formal Parameters */
    @Override
    public void visitFormalRef(FormalRef formalRef) {

    }

    @Override
    public void visitFormalVar(FormalVar formalVar) {

    }

    @Override
    public void visitRefOrValue(RefOrValue refOrValue) {

    }

    /* visit Labels and Types */
    @Override
    public void visitArray(Array array) {

    }

    @Override
    public void visitEnumeratedType(EnumeratedType enumeratedType) {

    }

    @Override
    public void visitPrimitiveType(PrimitiveType primitiveType) {

    }

    @Override
    public void visitSubRangeType(SubrangeType subrangeType) {

    }

    @Override
    public void visitTypeDefinition(TypeDefinition typeDefinition) {

    }

    @Override
    public void visitTypeId(TypeId typeId) {

    }

    /* visit Nodes */
    @Override
    public void visitBlock(Block block) {

    }

    @Override
    public void visitNode(Node node) {

    }

    @Override
    public void visitProgram(Program program) {

    }

    /* visit Operators */
    @Override
    public void visitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {

    }

    @Override
    public void visitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {

    }

    @Override
    public void visitRelationalOperator(RelationalOperator relationalOperator) {

    }

    @Override
    public void visitSign(Sign sign) {

    }

    /* visit Procedures and functions declarations */
    @Override
    public void visitFunctionDeclaration(FunctionDeclaration functionDeclaration) {

    }

    @Override
    public void visitFunctionDesignator(FunctionDesignator functionDesignator) {

    }

    @Override
    public void visitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {

    }

    /* visit Statements */
    @Override
    public void visitAssignStm(AssignmentStatement assignStm) {
        //assignStm.left.print(this);
        System.out.println(" + ");
        //assignStm.right.print(this);
        System.out.println("");
    }

    @Override
    public void visitCompStm(CompoundStatement compStm) {
        for (Statement stm : compStm.stmts) {
            //stm.print();
        }
    }

    @Override
    public void visitEmptyStm(EmptyStatement eStm) {
        System.out.println("");
    }

    @Override
    public void visitGotoStatement(GotoStatement gotoStatement) {

    }

    @Override
    public void visitIfStm(IfStatement ifStm) {
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
    public void visitProcedureStm(ProcedureDeclaration procedureStm) {
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
    public void visitWhileStm(WhileStatement whileStm) {
        System.out.print("while ");
        //whileStm.condition.print(this);
        System.out.println("\nbegin");
        //whileStm.body.visit();
        System.out.println("end");
    }

    /* visit Variables Declarations */
    @Override
    public void visitVariable(Variable variable) {

    }

    @Override
    public void visitVariableDeclaration(VariableDeclaration variableDeclaration) {

    }
}
