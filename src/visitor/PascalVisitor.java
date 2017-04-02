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
    Object VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter);
    Object VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant);
    Object VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant);

    /* visit Constants */
    Object VisitBooleanConstant(BooleanConstant booleanConstant);
    Object VisitCharacterConstant(CharacterConstant characterConstant);
    Object VisitSignedNumber(SignedNumber signedNumber);
    Object VisitUnsignedNumber(UnsignedNumber unsignedNumber);

    /* visit Expressions */
    Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression);
    Object VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression);
    Object VisitCharLiteral(CharLiteral charLiteral);
    Object VisitBooleanLiteral(BooleanLiteral booleanLiteral);
    Object VisitCharacterLiteral(CharacterLiteral characterLiteral);
    Object VisitIndexedVariable(IndexedVariable indexedVariable);
    Object VisitNotExpression(NotExpression notExpression);
    Object VisitNumberLiteral(NumberLiteral numberLiteral);
    Object VisitRelationalExpression(RelationalExpression relationalExpression);
    Object VisitSignedExpression(SignedExpression signedExpression);
    Object VisitStringLiteral(StringLiteral stringLiteral);

    /* visit Formal Parameters */
    Object visitFormalPar(FormalPar formalPar);

    /* visit Labels and Types */
    Object VisitArray(Array array);
    Object VisitEnumeratedType(EnumeratedType enumeratedType);
    Object VisitPrimitiveType(PrimitiveType primitiveType);
    Object VisitSubRangeType(SubrangeType subrangeType);
    Object VisitTypeDefinition(TypeDefinition typeDefinition);
    Object VisitTypeId(TypeId typeId);

    /* visit Nodes */
    Object VisitBlock(Block block);
    Object VisitIndexType(IndexType index);
    Object VisitProgram(Program program);

    /* visit Operators */
    Object VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator);
    Object VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator);
    Object VisitRelationalOperator(RelationalOperator relationalOperator);
    Object VisitSign(Sign sign);

    /* visit Procedures and functions declarations */
    Object VisitFunctionDeclaration(FunctionDeclaration functionDeclaration);
    Object VisitFunctionDesignator(FunctionDesignator functionDesignator);
    Object VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration);

    /* visit Statements */
    Object VisitAssignStm(AssignmentStatement assignStm);
    Object VisitCompStm(CompoundStatement compStm);
    Object VisitEmptyStm(EmptyStatement eStm);
    Object VisitGotoStatement(GotoStatement gotoStatement);
    Object VisitIfStm(IfStatement ifStm);
    Object VisitLabeledStm(LabeledStatement lblStm);
    Object VisitProcedureStm(ProcedureStatement procedureStm);
    Object VisitWhileStm(WhileStatement whileStm);

    /* visit Variables Declarations */
    Object VisitVariable(Variable variable);
    Object VisitVariableDeclaration(VariableDeclaration variableDeclaration);
}
