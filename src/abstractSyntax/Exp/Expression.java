package abstractSyntax.Exp;

import visitor.PascalVisitor;

public abstract class Expression {
    public abstract void accept(PascalVisitor visitor);
}