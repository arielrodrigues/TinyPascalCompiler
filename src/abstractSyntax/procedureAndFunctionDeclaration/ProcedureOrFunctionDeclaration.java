package abstractSyntax.procedureAndFunctionDeclaration;

import visitor.PascalVisitor;

public abstract class ProcedureOrFunctionDeclaration {
    public abstract Object accept(PascalVisitor visitor);
}