package abstractSyntax.FormalParameter;

import visitor.PasVisitor;

public abstract class FormalParameter {
    public abstract void accept(PasVisitor visitor);
}