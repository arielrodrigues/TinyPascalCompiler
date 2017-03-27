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

public interface PasVisitor {
    /* visit Conformant Array */
    void visitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter);
    void visitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant);
    void visitOneDimensionConformant(OneDimensionConformant oneDimensionConformant);

    /* visit Constants */
    void visitCharacterLiteral(CharacterLiteral characterLiteral);
    void visitSignedNumber(SignedNumber signedNumber);
    void visitUnsignedNumber(UnsignedNumber unsignedNumber);

    /* visit Expressions */
    void visitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression);
    void visitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression);
    void visitCharLiteral(CharLiteral charLiteral);
    void visitIndexedVariable(IndexedVariable indexedVariable);
    void visitNotExpression(NotExpression notExpression);
    void visitNumberLiteral(NumberLiteral numberLiteral);
    void visitRelationalExpression(RelationalExpression relationalExpression);
    void visitSignedExpression(SignedExpression signedExpression);
    void visitStringLiteral(StringLiteral stringLiteral);

    /* visit Formal Parameters */
    void visitFormalRef(FormalRef formalRef);
    void visitFormalVar(FormalVar formalVar);
    void visitRefOrValue(RefOrValue refOrValue);

    /* visit Labels and Types */
    void visitArray(Array array);
    void visitEnumeratedType(EnumeratedType enumeratedType);
    void visitPrimitiveType(PrimitiveType primitiveType);
    void visitSubRangeType(SubrangeType subrangeType);
    void visitTypeDefinition(TypeDefinition typeDefinition);
    void visitTypeId(TypeId typeId);

    /* visit Nodes */
    void visitBlock(Block block);
    void visitNode(Node node);
    void visitProgram(Program program);

    /* visit Operators */
    void visitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator);
    void visitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator);
    void visitRelationalOperator(RelationalOperator relationalOperator);
    void visitSign(Sign sign);

    /* visit Procedures and functions declarations */
    void visitFunctionDeclaration(FunctionDeclaration functionDeclaration);
    void visitFunctionDesignator(FunctionDesignator functionDesignator);
    void visitProcedureDeclaration(ProcedureDeclaration procedureDeclaration);

    /* visit Statements */
    void visitAssignStm(AssignmentStatement assignStm);
    void visitCompStm(CompoundStatement compStm);
    void visitEmptyStm(EmptyStatement eStm);
    void visitGotoStatement(GotoStatement gotoStatement);
    void visitIfStm(IfStatement ifStm);
    void visitProcedureStm(ProcedureDeclaration procedureStm);
    void visitWhileStm(WhileStatement whileStm);

    /* visit Variables Declarations */
    void visitVariable(Variable variable);
    void visitVariableDeclaration(VariableDeclaration variableDeclaration);

}
