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

public class PrettyPrint implements PascalVisitor {

    private int indentLevel = 0;
    private StringBuilder result = new StringBuilder();;

    public PrettyPrint (){};
    public PrettyPrint(Program program) {
        System.out.println(this.VisitProgram(program));
    }

    private void indent() { indentLevel++; }
    private void unindent() { --indentLevel; }

    private String printList(List<?> list, boolean parentheses) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (Object item : list.subList(1, list.size())) sb.append(", " + item);
        if (parentheses) {sb.insert(0, '('); sb.append(')');}
        return sb.toString();
    }

    private Object print (Object str, boolean indent) {
        String tabs = indent && indentLevel > 0?
                String.join("", Collections.nCopies(indentLevel, "   ")): "";
        return tabs + str;
    }

    /* visit Conformant Array */
    @Override
    public Object VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter) {
        StringBuilder result = new StringBuilder();
        if (conformantArrayParameter.mechanism.name() == "Ref") result.append(print("var ", false));
        result.append(print(conformantArrayParameter.name+": array [", false));
        result.append(conformantArrayParameter.schema.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {
        StringBuilder result = new StringBuilder();
        result.append(print(multiDimensionConformant.lowId+".."+multiDimensionConformant.highId+": ", false));
        result.append(multiDimensionConformant.rangeTy.accept(this));
        result.append(print("] of array [", false));
        result.append(multiDimensionConformant.elemTy.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {
        StringBuilder result = new StringBuilder();
        result.append(print(oneDimensionConformant.lowId+".."+oneDimensionConformant.highId+": ", false));
        result.append(oneDimensionConformant.rangeTy.accept(this));
        result.append(print("] of ", false));
        result.append(oneDimensionConformant.elemTy.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitConstantId(IdConstant idConstant) {
        return print(idConstant.id, false);
    }

    @Override
    public Object VisitBooleanConstant(BooleanConstant booleanConstant) {
        StringBuilder result = new StringBuilder();
        if (booleanConstant == BooleanConstant.TRUE)
            result.append(print("TRUE", false));
        else result.append(print("FALSE", false));
        return result.toString();
    }

    @Override
    public Object VisitCharacterConstant(CharacterConstant characterConstant) {
        return print("\'"+characterConstant.value+"\'", false);
    }

    /* visit Constants */
    @Override
    public Object VisitCharacterLiteral(CharacterLiteral characterLiteral) {
        return print("\'"+characterLiteral.value+"\'", false);
    }

    @Override
    public Object VisitSignedNumber(SignedNumber signedNumber) {
        StringBuilder result = new StringBuilder();
        result.append(signedNumber.sign.accept(this));
        result.append(signedNumber.unsNum.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitUnsignedNumber(UnsignedNumber unsignedNumber) {
        return print(unsignedNumber.value, false);
    }

    /* visit Expressions */
    @Override
    public Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {
        StringBuilder result = new StringBuilder();
        boolean leftparentheses = binaryArithmeticExpression.left instanceof BinaryArithmeticExpression;
        boolean rightparentheses = binaryArithmeticExpression.right instanceof BinaryArithmeticExpression;

        if (leftparentheses) result.append(print("(", false));
        result.append(binaryArithmeticExpression.left.accept(this));
        if (leftparentheses) result.append(print(")", false));
        result.append(binaryArithmeticExpression.op.accept(this));
        if (rightparentheses) result.append(print("(", false));
        result.append(binaryArithmeticExpression.right.accept(this));
        if (rightparentheses) result.append(print(")", false));

        return result.toString();
    }

    @Override
    public Object VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression) {
        StringBuilder result = new StringBuilder();
        result.append(print("(", false));
        result.append(binaryBooleanExpression.left.accept(this));
        result.append(binaryBooleanExpression.op.accept(this));
        result.append(binaryBooleanExpression.right.accept(this));
        result.append(print(")", false));
        return result.toString();
    }

    @Override
    public Object VisitCharLiteral(CharLiteral charLiteral) {
        return print(charLiteral.value, false);
    }

    @Override
    public Object VisitBooleanLiteral(BooleanLiteral booleanLiteral) {
        if (booleanLiteral == BooleanLiteral.TRUE)
            return print("TRUE", false);
        else
            return print("FALSE", false);
    }

    @Override
    public Object VisitIndexedVariable(IndexedVariable indexedVariable) {
        StringBuilder result = new StringBuilder();
        result.append(indexedVariable.var.accept(this))
                .append("[").append(indexedVariable.index.accept(this)).append("]");
        return result.toString();
    }

    @Override
    public Object VisitNotExpression(NotExpression notExpression) {
        StringBuilder result = new StringBuilder();
        result.append(print("not (", false));
        result.append(notExpression.exp.accept(this));
        result.append(print(")", false));
        return result.toString();
    }

    @Override
    public Object VisitNumberLiteral(NumberLiteral numberLiteral) {
        return print(numberLiteral.value, false);
    }

    @Override
    public Object VisitRelationalExpression(RelationalExpression relationalExpression) {
        StringBuilder result = new StringBuilder();
        result.append(relationalExpression.left.accept(this));
        result.append(relationalExpression.op.accept(this));
        result.append(relationalExpression.right.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitSignedExpression(SignedExpression signedExpression) {
        StringBuilder result = new StringBuilder();
        result.append(print("(", false));
        result.append(signedExpression.sign.accept(this));
        result.append(signedExpression.exp.accept(this));
        result.append(print(")", false));
        return result.toString();
    }

    @Override
    public Object VisitStringLiteral(StringLiteral stringLiteral) {
        return print(stringLiteral.value, false);
    }

    /* visit Formal Parameters */

    @Override
    public Object VisitFormalPar(FormalPar formalPar) {
        StringBuilder result = new StringBuilder();
        if (formalPar.mechanism == RefOrValue.Ref)
            result.append(print("var ", false));
        result.append(print(formalPar.name+": ", false));
        result.append(formalPar.type.accept(this));
        return result.toString();
    }

    /* visit Labels and Types */
    @Override
    public Object VisitArray(Array array) {
        StringBuilder result = new StringBuilder();
        result.append(print("array [", false));
        result.append(array.range.accept(this));
        result.append(print("] of ", false));
        result.append(array.elemTy.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitEnumeratedType(EnumeratedType enumeratedType) {
        return print(printList(enumeratedType.newConstants, true),false);
    }

    @Override
    public Object VisitPrimitiveType(PrimitiveType primitiveType) {
        if (primitiveType == PrimitiveType.INTEGER) return print("integer", false);
        else if (primitiveType == PrimitiveType.CHAR) return print("char", false);
        else if (primitiveType == PrimitiveType.STRING) return print("string", false);
        else if (primitiveType == PrimitiveType.BOOLEAN) return print("boolean", false);
        return null;
    }

    @Override
    public Object VisitSubRangeType(SubrangeType subrangeType) {
        StringBuilder result = new StringBuilder();
        result.append(subrangeType.low.accept(this));
        result.append(print("..", false));
        result.append(subrangeType.high.accept(this));
        return result.toString();
    }

    @Override
    public Object VisitTypeDefinition(TypeDefinition typeDefinition) {
        StringBuilder result = new StringBuilder();
        result.append(print(typeDefinition.id + ": ", true));
        result.append(typeDefinition.ty.accept(this));
        result.append(print(";\n", false));
        return result.toString();
    }

    @Override
    public Object VisitTypeId(IdType idType) {
        return print(idType.id, false);
    }

    /* visit Nodes */
    @Override
    public Object VisitBlock(Block block) {
        StringBuilder result = new StringBuilder();
        indent();
        if (!block.labels.isEmpty()) {
            result.append(print("Label ", true));
            result.append(print(printList(block.labels, false)+";\n", false));
        }
        if (!block.typeDefs.isEmpty()) {
            result.append(print("Type\n", true));
            indent();
            for (TypeDefinition typeDef : block.typeDefs) result.append(typeDef.accept(this));
            unindent();
        }
        if (!block.varDecs.isEmpty()) {
            result.append(print ("Var\n", true));
            indent();
            for (VariableDeclaration varDec : block.varDecs) result.append(varDec.accept(this));
            unindent();
        }
        if (!block.subprogs.isEmpty()) {
            for (ProcedureOrFunctionDeclaration subprog : block.subprogs) result.append(subprog.accept(this));
            unindent();
        }
        result.append(print("Begin\n", true));
        indent();
        result.append(block.body.accept(this));
        unindent();
        result.append(print("\n", false));
        result.append(print("End", true));
        unindent();
        return result.toString();
    }

    @Override
    public Object VisitProgram(Program program) {
        StringBuilder result = new StringBuilder();
        result.append("Program " + program.id + "(");
        if (!program.io.isEmpty()) result.append(print(printList(program.io, false), false));
        result.append(");\n");
        result.append(program.block.accept(this));
        result.append(print(".", false));

        return result.toString();
    }

    /* visit Operators */
    @Override
    public Object VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {
        switch (binaryArithmeticOperator.name()) {
            case "DIV":   return print(" DIV ", false);
            case "MOD":   return print(" MOD ", false);
            case "PLUS":  return print(" + ", false);
            case "TIMES": return print(" * ", false);
            case "MINUS": return print(" - ", false);
            default: return null;
        }
    }

    @Override
    public Object VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {
        switch (binaryBooleanOperator.name()) {
            case "AND": return print(") AND (", false);
            case "OR": return print(") OR (", false);
            default: return null;
        }
    }

    @Override
    public Object VisitRelationalOperator(RelationalOperator relationalOperator) {
        switch (relationalOperator.name()) {
            case "EQ": return print(" = ", false);
            case "NEQ": return print(" <> ", false);
            case "LT": return print(" < ", false);
            case "GT": return print(" > ", false);
            case "LTE": return print(" =< ", false);
            case "GTE": return print(" >= ", false);
            default: return null;
        }
    }

    @Override
    public Object VisitSign(Sign sign) {
        return (sign.name().matches("PLUS"))? " + " : " - ";
    }

    /* visit Procedures and functions declarations */
    @Override
    public Object VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        StringBuilder result = new StringBuilder();
        result.append(print("Function "+functionDeclaration.nm+"(", true));

        result.append(functionDeclaration.formals.get(0).accept(this));
        for (FormalParameter formal : functionDeclaration.formals.subList(1, functionDeclaration.formals.size())) {
            result.append(print("; ", false));
            if (formal instanceof FormalPar) {
                FormalPar par = (FormalPar) formal;
                result.append(par.accept(this));
            }
        }
        result.append(print(") : ", false));
        result.append(functionDeclaration.resultTy.accept(this));
        result.append(print (";\n", false));
        result.append(functionDeclaration.body.accept(this));
        result.append(print (";\n", false));

        return result.toString();
    }

    @Override
    public Object VisitFunctionDesignator(FunctionDesignator functionDesignator) {
        StringBuilder result = new StringBuilder();
        result.append(print(functionDesignator.name + "(", false));
        result.append(functionDesignator.actuals.get(0).accept(this));
        for (Expression exp : functionDesignator.actuals.subList(1, functionDesignator.actuals.size())) {
            result.append(print(", ", false));
            result.append(exp.accept(this));
        }
        result.append(print (")", false));
        return result.toString();
    }

    @Override
    public Object VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        StringBuilder result = new StringBuilder();
        result.append(print("Procedure "+procedureDeclaration.nm+"(", true));
        if (procedureDeclaration.formals.size() > 0) {
            result.append(procedureDeclaration.formals.get(0).accept(this));
            for (FormalParameter formal : procedureDeclaration.formals.subList(1, procedureDeclaration.formals.size())) {
                result.append(print("; ", false));
                result.append(formal.accept(this));
            }
        }
        result.append(print(");\n", false));
        result.append(procedureDeclaration.body.accept(this));
        result.append(print (";\n", false));
        return result.toString();
    }

    /* visit Statements */
    @Override
    public Object VisitAssignStm(AssignmentStatement assignStm) {
        return assignStm.left.accept(this) +
                (String) print(" := ", false) +
                    assignStm.right.accept(this);
    }

    @Override
    public Object VisitCompStm(CompoundStatement compStm) {
        StringBuilder result = new StringBuilder();
        // we must remove all empty stmts before print
        int i = 0;
        Statement _stm = compStm.stmts.get(i++);
        while (_stm instanceof EmptyStatement)
            if (compStm.stmts.size() > i+1) _stm = compStm.stmts.get(i++);

        result.append(print("", true));
        result.append(_stm.accept(this));

        for (Statement stm : compStm.stmts.subList(i, compStm.stmts.size())) {
            if (stm instanceof EmptyStatement) continue;
            result.append(print(";\n", false));
            result.append(print("", true));
            result.append(stm.accept(this));
        }
        return result.toString();
    }

    @Override
    public Object VisitEmptyStm(EmptyStatement eStm) {
        return null;
    }

    @Override
    public Object VisitGotoStatement(GotoStatement gotoStatement) {
        return print("goto ", false) + (String) gotoStatement.label.accept(this);
    }

    @Override
    public Object VisitIfStm(IfStatement ifStm) {
        StringBuilder result = new StringBuilder();

        result.append(print("if (", false))
                .append(ifStm.condition.accept(this))
                .append(print(") then\n", false));
        indent();
        if (ifStm.thenPart instanceof CompoundStatement) {
            result.append(print("begin\n", true));
            indent();
            result.append(ifStm.thenPart.accept(this)).append("\n");
            unindent();
            result.append(print("end", true));
        } else {
            result.append(print("", true))
                    .append(ifStm.thenPart.accept(this));
        }
        unindent();

        if (ifStm.elsePart != null) {
            result.append(print("\n", false))
                    .append(print("else\n", true));
            indent();
            if (ifStm.elsePart instanceof CompoundStatement) {
                result.append(print("begin\n", true));
                indent();
                result.append(ifStm.elsePart.accept(this))
                        .append("\n");
                unindent();
                result.append(print("end", true));
            } else {
                result.append(print("", true))
                        .append(ifStm.elsePart.accept(this));
            }
            unindent();
        }

        return result.toString();
    }

    @Override
    public Object VisitLabeledStm(LabeledStatement lblStm) {
        return print(lblStm.label +": ", false) + (String) lblStm.stm.accept(this);
    }

    @Override
    public Object VisitProcedureStm(ProcedureStatement procedureStm) {
        StringBuilder result = new StringBuilder();

        result.append(procedureStm.name+"(");
        if (!procedureStm.actuals.isEmpty()) {
            result.append(procedureStm.actuals.get(0).accept(this));
            for (Expression exp : procedureStm.actuals.subList(1, procedureStm.actuals.size())) {
                result.append(", ")
                        .append(exp.accept(this));
            }
        }
        result.append(")");
        return result.toString();
    }

    @Override
    public Object VisitWhileStm(WhileStatement whileStm) {
        StringBuilder result = new StringBuilder();

        result.append("while (")
                .append(whileStm.condition.accept(this))
                    .append(")");
        indent();
        if (whileStm.body instanceof CompoundStatement) {
           result.append("\n")
                   .append(print("begin\n", true));
            indent();
            result.append(whileStm.body.accept(this))
                    .append("\n");
            unindent();
            result.append(print("end", true));
        } else {
            result.append("\n")
                    .append(print("", true))
                        .append(whileStm.body.accept(this));
        }
        unindent();
        return result.toString();
    }

    /* visit Variables Declarations */
    @Override
    public Object VisitIdExpression(IdExpression idExpression) {
        return print(idExpression.name, false);
    }

    @Override
    public Object VisitVariableDeclaration(VariableDeclaration variableDeclaration) {
        return print(variableDeclaration.id + ": ", true) +
                (String) variableDeclaration.ty.accept(this) + ";\n";
    }
}