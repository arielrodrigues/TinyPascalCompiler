package abstractSyntax.procedureAndFunctionDeclaration;

import visitor.PasVisitor;

public abstract class ProcedureOrFunctionDeclaration {
    public abstract void accept(PasVisitor visitor);
}