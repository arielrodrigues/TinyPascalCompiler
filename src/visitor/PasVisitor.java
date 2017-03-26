package visitor;
import abstractSyntax.Exp.*;
import abstractSyntax.labelsAndTypes.*;
import abstractSyntax.Operator.*;
import abstractSyntax.procedureAndFunctionDeclaration.*;
import abstractSyntax.programHeading.*;
import abstractSyntax.Stm.*;
import abstractSyntax.variablesDeclaration.*;

public interface PasVisitor {


    /* visit Statements */
    void visitAssignStm(AssignmentStatement assignStm);
    void visitCompStm(CompoundStatement compStm);
    void visitEmptyStm(EmptyStatement eStm);
    void visitIfStm(IfStatement ifStm);
    void visitProcedureStm(ProcedureDeclaration procedureStm);
    void visitWhileStm(WhileStatement whileStm);
}
