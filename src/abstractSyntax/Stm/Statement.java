package abstractSyntax.Stm;

import visitor.PasVisitor;

public abstract class Statement {
    public abstract void accept(PasVisitor visitor);
}