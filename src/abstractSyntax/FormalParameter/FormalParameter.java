package abstractSyntax.FormalParameter;

import visitor.PascalVisitor;

public abstract class FormalParameter {
    public abstract void accept(PascalVisitor visitor);
}