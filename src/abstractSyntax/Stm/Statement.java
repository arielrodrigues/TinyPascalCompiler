package abstractSyntax.Stm;

import visitor.PascalVisitor;

public abstract class Statement {
    public abstract Object accept(PascalVisitor visitor);
}