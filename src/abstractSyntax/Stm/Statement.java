package abstractSyntax.Stm;

import visitor.PascalVisitor;

public abstract class Statement {
    public abstract void accept(PascalVisitor visitor);
}