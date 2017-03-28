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

public interface PascalVisitor {
    /* visit Conformant Array */
    void VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter);
    void VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant);
    void VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant);

    /* visit Constants */
    void VisitCharacterLiteral(CharacterLiteral characterLiteral);
    void VisitSignedNumber(SignedNumber signedNumber);
    void VisitUnsignedNumber(UnsignedNumber unsignedNumber);

    /* visit Expressions */
    void VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression);
    void VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression);
    void VisitCharLiteral(CharLiteral charLiteral);
    void VisitIndexedVariable(IndexedVariable indexedVariable);
    void VisitNotExpression(NotExpression notExpression);
    void VisitNumberLiteral(NumberLiteral numberLiteral);
    void VisitRelationalExpression(RelationalExpression relationalExpression);
    void VisitSignedExpression(SignedExpression signedExpression);
    void VisitStringLiteral(StringLiteral stringLiteral);

    /* visit Formal Parameters */
    void VisitFormalRef(FormalRef formalRef);
    void VisitFormalVar(FormalVar formalVar);
    void VisitRefOrValue(RefOrValue refOrValue);

    /* visit Labels and Types */
    void VisitArray(Array array);
    void VisitEnumeratedType(EnumeratedType enumeratedType);
    void VisitPrimitiveType(PrimitiveType primitiveType);
    void VisitSubRangeType(SubrangeType subrangeType);
    void VisitTypeDefinition(TypeDefinition typeDefinition);
    void VisitTypeId(TypeId typeId);

    /* visit Nodes */
    void VisitBlock(Block block);
    void VisitProgram(Program program);

    /* visit Operators */
    void VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator);
    void VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator);
    void VisitRelationalOperator(RelationalOperator relationalOperator);
    void VisitSign(Sign sign);

    /* visit Procedures and functions declarations */
    void VisitFunctionDeclaration(FunctionDeclaration functionDeclaration);
    void VisitFunctionDesignator(FunctionDesignator functionDesignator);
    void VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration);

    /* visit Statements */
    void VisitAssignStm(AssignmentStatement assignStm);
    void VisitCompStm(CompoundStatement compStm);
    void VisitEmptyStm(EmptyStatement eStm);
    void VisitGotoStatement(GotoStatement gotoStatement);
    void VisitIfStm(IfStatement ifStm);
    void VisitProcedureStm(ProcedureDeclaration procedureStm);
    void VisitWhileStm(WhileStatement whileStm);

    /* visit Variables Declarations */
    void VisitVariable(Variable variable);
    void VisitVariableDeclaration(VariableDeclaration variableDeclaration);
}
