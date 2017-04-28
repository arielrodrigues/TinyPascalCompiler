package visitor;

import abstractSyntax.ConformantArray.*;
import abstractSyntax.Constant.*;
import abstractSyntax.Exp.*;
import abstractSyntax.FormalParameter.*;
import abstractSyntax.Node.*;
import abstractSyntax.Operator.*;
import abstractSyntax.Stm.*;
import abstractSyntax.labelsAndTypes.*;
import abstractSyntax.procedureAndFunctionDeclaration.*;
import abstractSyntax.variablesDeclaration.*;

public class TreeTranslator implements PascalVisitor {

    public TreeTranslator (Program prog) {
        VisitProgram(prog);
    }

    @Override
    public Object VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter) {
        return null;
    }

    @Override
    public Object VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {
        return null;
    }

    @Override
    public Object VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {
        return null;
    }

    @Override
    public Object VisitConstantId(IdConstant idConstant) {
        return null;
    }

    @Override
    public Object VisitBooleanConstant(BooleanConstant booleanConstant) {
        return null;
    }

    @Override
    public Object VisitCharacterConstant(CharacterConstant characterConstant) {
        return null;
    }

    @Override
    public Object VisitSignedNumber(SignedNumber signedNumber) {
        return null;
    }

    @Override
    public Object VisitUnsignedNumber(UnsignedNumber unsignedNumber) {
        return null;
    }

    @Override
    public Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {
        return null;
    }

    @Override
    public Object VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression) {
        return null;
    }

    @Override
    public Object VisitCharLiteral(CharLiteral charLiteral) {
        return null;
    }

    @Override
    public Object VisitBooleanLiteral(BooleanLiteral booleanLiteral) {
        return null;
    }

    @Override
    public Object VisitCharacterLiteral(CharacterLiteral characterLiteral) {
        return null;
    }

    @Override
    public Object VisitIndexedVariable(IndexedVariable indexedVariable) {
        return null;
    }

    @Override
    public Object VisitNotExpression(NotExpression notExpression) {
        return null;
    }

    @Override
    public Object VisitNumberLiteral(NumberLiteral numberLiteral) {
        return null;
    }

    @Override
    public Object VisitRelationalExpression(RelationalExpression relationalExpression) {
        return null;
    }

    @Override
    public Object VisitSignedExpression(SignedExpression signedExpression) {
        return null;
    }

    @Override
    public Object VisitStringLiteral(StringLiteral stringLiteral) {
        return null;
    }

    @Override
    public Object VisitFormalPar(FormalPar formalPar) {
        return null;
    }

    @Override
    public Object VisitArray(Array array) {
        return null;
    }

    @Override
    public Object VisitEnumeratedType(EnumeratedType enumeratedType) {
        return null;
    }

    @Override
    public Object VisitPrimitiveType(PrimitiveType primitiveType) {
        return null;
    }

    @Override
    public Object VisitSubRangeType(SubrangeType subrangeType) {
        return null;
    }

    @Override
    public Object VisitTypeDefinition(TypeDefinition typeDefinition) {
        return null;
    }

    @Override
    public Object VisitTypeId(IdType idType) {
        return null;
    }

    @Override
    public Object VisitBlock(Block block) {
        return null;
    }

    @Override
    public Object VisitProgram(Program program) {
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

    @Override
    public Object VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        return null;
    }

    @Override
    public Object VisitFunctionDesignator(FunctionDesignator functionDesignator) {
        return null;
    }

    @Override
    public Object VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        return null;
    }

    @Override
    public Object VisitAssignStm(AssignmentStatement assignStm) {
        return null;
    }

    @Override
    public Object VisitCompStm(CompoundStatement compStm) {
        return null;
    }

    @Override
    public Object VisitEmptyStm(EmptyStatement eStm) {
        return null;
    }

    @Override
    public Object VisitGotoStatement(GotoStatement gotoStatement) {
        return null;
    }

    @Override
    public Object VisitIfStm(IfStatement ifStm) {
        return null;
    }

    @Override
    public Object VisitLabeledStm(LabeledStatement lblStm) {
        return null;
    }

    @Override
    public Object VisitProcedureStm(ProcedureStatement procedureStm) {
        return null;
    }

    @Override
    public Object VisitWhileStm(WhileStatement whileStm) {
        return null;
    }

    @Override
    public Object VisitIdExpression(IdExpression idExpression) {
        return null;
    }

    @Override
    public Object VisitVariableDeclaration(VariableDeclaration variableDeclaration) {
        return null;
    }
}
