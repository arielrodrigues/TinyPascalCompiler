package visitor;

import abstractSyntax.Exp.*;
import abstractSyntax.FormalParameter;
import abstractSyntax.labelsAndTypes.*;
import abstractSyntax.Operator.*;
import abstractSyntax.procedureAndFunctionDeclaration.*;
import abstractSyntax.programHeading.*;
import abstractSyntax.Stm.*;
import abstractSyntax.variablesDeclaration.*;

public class PrettyprintVisitor implements PasVisitor {

    /* visit Statements */
    @Override
    public void visitAssignStm(AssignmentStatement assignStm) {
        //assignStm.left.accept(this);
        System.out.println(" + ");
        //assignStm.right.accept(this);
        System.out.println("");
    }

    @Override
    public void visitCompStm(CompoundStatement compStm) {
        for (Statement stm : compStm.stmts) {
            //stm.accept();
        }
    }

    @Override
    public void visitEmptyStm(EmptyStatement eStm) {
        System.out.println("");
    }

    @Override
    public void visitIfStm(IfStatement ifStm) {
        // colocar uns testes p add begin end e parenteses
        System.out.print("if ");
        //ifStm.condition.accept(this);
        System.out.print(" then ");
        //ifStm.thenPart.accept(this);
        System.out.print(" else ");
        //ifStm.elsePart.accept(this);
        System.out.print('\n');
    }

    @Override
    public void visitProcedureStm(ProcedureDeclaration procedureStm) {
        System.out.print("Procedure ");
        //procedureStm.nm.accept(this);
        System.out.print("(");
        for (FormalParameter fPar: procedureStm.formals) {
            //fPar.accept(this);
            System.out.print(", ");
        }
        System.out.println(")\nbegin");
        //procedureStm.body.accept(this);
        System.out.println("end;");
    }

    @Override
    public void visitWhileStm(WhileStatement whileStm) {
        System.out.print("while ");
        //whileStm.condition.accept(this);
        System.out.println("\nbegin");
        //whileStm.body.visit();
        System.out.println("end");
    }
}
