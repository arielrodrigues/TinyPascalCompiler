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

    public PrettyPrint(Program program) {
        this.VisitProgram(program);
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
        String tabs = indent? String.join("", Collections.nCopies(indentLevel, "   ")): "";
        System.out.print(tabs + str);
        return null;
    }

    /* visit Conformant Array */
    @Override
    public Object VisitConformantArrayParameter(ConformantArrayParameter conformantArrayParameter) {
        if (conformantArrayParameter.mechanism.name() == "Ref") print("var ", false);
        print(conformantArrayParameter.name+": array [", false);
        conformantArrayParameter.schema.accept(this);
        return null;
    }

    @Override
    public Object VisitMultiDimensionConformant(MultiDimensionConformant multiDimensionConformant) {
        print(multiDimensionConformant.lowId+".."+multiDimensionConformant.highId+": ", false);
        multiDimensionConformant.rangeTy.accept(this);
        print("] of array [", false);
        multiDimensionConformant.elemTy.accept(this);
        return null;
    }

    @Override
    public Object VisitOneDimensionConformant(OneDimensionConformant oneDimensionConformant) {
        print(oneDimensionConformant.lowId+".."+oneDimensionConformant.highId+": ", false);
        oneDimensionConformant.rangeTy.accept(this);
        print("] of ", false);
        oneDimensionConformant.elemTy.accept(this);
        return null;
    }

    @Override
    public Object VisitConstantId(IdConstant idConstant) {
        print(idConstant.id, false);
        return null;
    }

    @Override
    public Object VisitBooleanConstant(BooleanConstant booleanConstant) {
        if (booleanConstant == BooleanConstant.TRUE)
            print("TRUE", false);
        else print("FALSE", false);
        return null;
    }

    @Override
    public Object VisitCharacterConstant(CharacterConstant characterConstant) {
        print(characterConstant.value, false);
        return null;
    }

    /* visit Constants */
    @Override
    public Object VisitCharacterLiteral(CharacterLiteral characterLiteral) {
        print(characterLiteral.value, false);
        return null;
    }

    @Override
    public Object VisitSignedNumber(SignedNumber signedNumber) {
        signedNumber.sign.accept(this);
        signedNumber.unsNum.accept(this);
        return null;
    }

    @Override
    public Object VisitUnsignedNumber(UnsignedNumber unsignedNumber) {
        print(unsignedNumber.value, false);
        return null;
    }

    /* visit Expressions */
    @Override
    public Object VisitBinaryArithmeticExpression(BinaryArithmeticExpression binaryArithmeticExpression) {
        boolean leftparentheses = binaryArithmeticExpression.left instanceof BinaryArithmeticExpression;
        boolean rightparentheses = binaryArithmeticExpression.right instanceof BinaryArithmeticExpression;

        if (leftparentheses) print("(", false);
        binaryArithmeticExpression.left.accept(this);
        if (leftparentheses) print(")", false);
        binaryArithmeticExpression.op.accept(this);
        if (rightparentheses) print("(", false);
        binaryArithmeticExpression.right.accept(this);
        if (rightparentheses) print(")", false);

        return null;
    }

    @Override
    public Object VisitBinaryBooleanExpression(BinaryBooleanExpression binaryBooleanExpression) {
        print("(", false);
        binaryBooleanExpression.left.accept(this);
        binaryBooleanExpression.op.accept(this);
        binaryBooleanExpression.right.accept(this);
        print(")", false);
        return null;
    }

    @Override
    public Object VisitCharLiteral(CharLiteral charLiteral) {
        print(charLiteral.value, false);
        return null;
    }

    @Override
    public Object VisitBooleanLiteral(BooleanLiteral booleanLiteral) {
        if (booleanLiteral == BooleanLiteral.TRUE)
            print("TRUE", false);
        else print("FALSE", false);
        return null;
    }

    @Override
    public Object VisitIndexedVariable(IndexedVariable indexedVariable) {
        indexedVariable.var.accept(this);
        print(" = ", false);
        indexedVariable.index.accept(this);
        return null;
    }

    @Override
    public Object VisitNotExpression(NotExpression notExpression) {
        print("not (", false);
        notExpression.exp.accept(this);
        print(")", false);
        return null;
    }

    @Override
    public Object VisitNumberLiteral(NumberLiteral numberLiteral) {
        print(numberLiteral.value, false);
        return null;
    }

    @Override
    public Object VisitRelationalExpression(RelationalExpression relationalExpression) {
        relationalExpression.left.accept(this);
        relationalExpression.op.accept(this);
        relationalExpression.right.accept(this);
        return null;
    }

    @Override
    public Object VisitSignedExpression(SignedExpression signedExpression) {
        signedExpression.sign.accept(this);
        signedExpression.exp.accept(this);
        return null;
    }

    @Override
    public Object VisitStringLiteral(StringLiteral stringLiteral) {
        print(stringLiteral.value, false);
        return null;
    }

    /* visit Formal Parameters */

    @Override
    public Object VisitFormalPar(FormalPar formalPar) {
        if (formalPar.mechanism == RefOrValue.Ref) print("var ", false);
        print(formalPar.name+": ", false);
        formalPar.type.accept(this);
        return null;
    }

    /* visit Labels and Types */
    @Override
    public Object VisitArray(Array array) {
        print("array [", false);
        array.range.accept(this);
        print("] of ", false);
        array.elemTy.accept(this);
        return null;
    }

    @Override
    public Object VisitEnumeratedType(EnumeratedType enumeratedType) {
        print(printList(enumeratedType.newConstants, true),false);
        return null;
    }

    @Override
    public Object VisitPrimitiveType(PrimitiveType primitiveType) {
        if (primitiveType == PrimitiveType.INTEGER) print("integer", false);
        else if (primitiveType == PrimitiveType.CHAR) print("char", false);
        else if (primitiveType == PrimitiveType.STRING) print("string", false);
        else if (primitiveType == PrimitiveType.BOOLEAN) print("boolean", false);
        return null;
    }

    @Override
    public Object VisitSubRangeType(SubrangeType subrangeType) {
        subrangeType.low.accept(this);
        print("..", false);
        subrangeType.high.accept(this);
        return null;
    }

    @Override
    public Object VisitTypeDefinition(TypeDefinition typeDefinition) {
        print(typeDefinition.id + ": ", true);
        typeDefinition.ty.accept(this);
        print(";\n", false);
        return null;
    }

    @Override
    public Object VisitTypeId(IdType idType) {
        print(idType.id, false);
        return null;
    }

    /* visit Nodes */
    @Override
    public Object VisitBlock(Block block) {
        indent();
        if (!block.labels.isEmpty()) {
            print("Label ", true);
            print(printList(block.labels, false)+";\n", false);
        }
        if (!block.typeDefs.isEmpty()) {
            print("Type\n", true);
            indent();
            for (TypeDefinition typeDef : block.typeDefs) typeDef.accept(this);
            unindent();
        }
        if (!block.varDecs.isEmpty()) {
            print ("Var\n", true);
            indent();
            for (VariableDeclaration varDec : block.varDecs) varDec.accept(this);
            unindent();
        }
        if (!block.subprogs.isEmpty()) {
            for (ProcedureOrFunctionDeclaration subprog : block.subprogs) subprog.accept(this);
            unindent();
        }
        print("Begin\n", true);
        indent();
        block.body.accept(this);
        unindent();
        print("\n", false);
        print("End", true);
        unindent();
        return null;
    }

    @Override
    public Object VisitProgram(Program program) {
        System.out.print("Program " + program.id + "(");
        if (!program.io.isEmpty()) print(printList(program.io, false), false);
        System.out.print(");\n");
        program.block.accept(this);
        print(".", false);


        return null;
    }

    /* visit Operators */
    @Override
    public Object VisitBinaryArithmeticOperator(BinaryArithmeticOperator binaryArithmeticOperator) {
        switch (binaryArithmeticOperator.name()) {
            case "DIV":   print(" DIV ", false); break;
            case "MOD":   print(" MOD ", false); break;
            case "PLUS":  print(" + ", false); break;
            case "TIMES": print(" * ", false); break;
            case "MINUS": print(" - ", false); break;
        }
        return null;
    }

    @Override
    public Object VisitBinaryBooleanOperator(BinaryBooleanOperator binaryBooleanOperator) {
        switch (binaryBooleanOperator.name()) {
            case "AND": print(") AND (", false); break;
            case "OR": print(") OR (", false); break;
        }
        return null;
    }

    @Override
    public Object VisitRelationalOperator(RelationalOperator relationalOperator) {
        switch (relationalOperator.name()) {
            case "EQ": print(" = ", false); break;
            case "NEQ": print(" <> ", false); break;
            case "LT": print(" < ", false); break;
            case "GT": print(" > ", false); break;
            case "LTE": print(" =< ", false); break;
            case "GTE": print(" >= ", false); break;
        }
        return null;
    }

    @Override
    public Object VisitSign(Sign sign) {
        String signSymbol = (sign.name().matches("PLUS"))? " + " : " - ";
        print(signSymbol, false);
        return null;
    }

    /* visit Procedures and functions declarations */
    @Override
    public Object VisitFunctionDeclaration(FunctionDeclaration functionDeclaration) {
        print("Function "+functionDeclaration.nm+"(", true);

        functionDeclaration.formals.get(0).accept(this);
        for (FormalParameter formal : functionDeclaration.formals.subList(1, functionDeclaration.formals.size())) {
            print("; ", false);
            if (formal instanceof FormalPar) {
                FormalPar par = (FormalPar) formal;
                par.accept(this);
            }
        }
        print(") : ", false);
        functionDeclaration.resultTy.accept(this); print (";\n", false);
        functionDeclaration.body.accept(this); print (";\n", false);
        return null;
    }

    @Override
    public Object VisitFunctionDesignator(FunctionDesignator functionDesignator) {
        print(functionDesignator.name + "(", false);
        functionDesignator.actuals.get(0).accept(this);
        for (Expression exp : functionDesignator.actuals.subList(1, functionDesignator.actuals.size())) {
            print(", ", false); exp.accept(this);
        }
        print (")", false);
        return null;
    }

    @Override
    public Object VisitProcedureDeclaration(ProcedureDeclaration procedureDeclaration) {
        print("Procedure "+procedureDeclaration.nm+"(", true);
        for (FormalParameter formal : procedureDeclaration.formals) formal.accept(this);
        print(");\n", false);
        procedureDeclaration.body.accept(this); print (";\n", false);
        return null;
    }

    /* visit Statements */
    @Override
    public Object VisitAssignStm(AssignmentStatement assignStm) {
        assignStm.left.accept(this);
        print(" := ", false);
        assignStm.right.accept(this);
        return null;
    }

    @Override
    public Object VisitCompStm(CompoundStatement compStm) {
        // we must remove all empty stmts before print
        int i = 0;
        Statement _stm = compStm.stmts.get(i++);
        while (_stm instanceof EmptyStatement) _stm = compStm.stmts.get(i++);
        print("", true); _stm.accept(this);

        for (Statement stm : compStm.stmts.subList(i, compStm.stmts.size())) {
            if (stm instanceof EmptyStatement) continue;
            print(";\n", false);
            print("", true);
            stm.accept(this);
        }
        return null;
    }

    @Override
    public Object VisitEmptyStm(EmptyStatement eStm) {
        return null;
    }

    @Override
    public Object VisitGotoStatement(GotoStatement gotoStatement) {
        print("goto ", false);
        gotoStatement.label.accept(this);
        return null;
    }

    @Override
    public Object VisitIfStm(IfStatement ifStm) {
        print("if (", false);
        ifStm.condition.accept(this);
        print(") then\n", false);
        indent();
        if (ifStm.thenPart instanceof CompoundStatement) {
            print("begin\n", true);
            indent();
            ifStm.thenPart.accept(this);
            print("\n", false);
            unindent();
            print("end", true);
        } else {
            print("", true);
            ifStm.thenPart.accept(this);
        }
        unindent();

        if (ifStm.elsePart != null) {
            print("\n", false);
            print("else\n", true);
            indent();
            if (ifStm.elsePart instanceof CompoundStatement) {
                print("begin\n", true);
                indent();
                ifStm.elsePart.accept(this);
                print("\n", false);
                unindent();
                print("end", true);
            } else {
                print("", true);
                ifStm.elsePart.accept(this);
            }
            unindent();
        }

        return null;
    }

    @Override
    public Object VisitLabeledStm(LabeledStatement lblStm) {
        print(lblStm.label +": ", false);
        lblStm.stm.accept(this);
        return null;
    }

    @Override
    public Object VisitProcedureStm(ProcedureStatement procedureStm) {
        print(procedureStm.name+"(", false);
        if (!procedureStm.actuals.isEmpty()) {
            procedureStm.actuals.get(0).accept(this);
            for (Expression exp : procedureStm.actuals.subList(1, procedureStm.actuals.size())) {
                print(", ", false);
                exp.accept(this);
            }
        }
        print(")", false);
        return null;
    }

    @Override
    public Object VisitWhileStm(WhileStatement whileStm) {
        print("while (", false);
        whileStm.condition.accept(this);
        print(")", false);
        indent();
        if (whileStm.body instanceof CompoundStatement) {
            print("\n", false);
            print("begin\n", true);
            indent();
            whileStm.body.accept(this);
            print("\n", false);
            unindent();
            print("end", true);
        } else {
            print("\n", false);
            print("", true);
            whileStm.body.accept(this);
        }
        unindent();
        return null;
    }

    /* visit Variables Declarations */
    @Override
    public Object VisitIdExpression(IdExpression idExpression) {
        print(idExpression.name, false);
        return null;
    }

    @Override
    public Object VisitVariableDeclaration(VariableDeclaration variableDeclaration) {
        print(variableDeclaration.id + ": ", true);
        variableDeclaration.ty.accept(this);
        print(";\n", false);
        return null;
    }
}