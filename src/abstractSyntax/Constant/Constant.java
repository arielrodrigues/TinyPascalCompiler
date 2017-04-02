package abstractSyntax.Constant;

import visitor.PascalVisitor;

public abstract class Constant {
    public abstract void accept(PascalVisitor visitor);
}