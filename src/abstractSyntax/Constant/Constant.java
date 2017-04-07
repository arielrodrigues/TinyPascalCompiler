package abstractSyntax.Constant;

import visitor.PascalVisitor;

public abstract class Constant {
    public abstract Object accept(PascalVisitor visitor);
}