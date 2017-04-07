package abstractSyntax.Exp;

import visitor.PascalVisitor;

public abstract class Expression {
    public abstract Object accept(PascalVisitor visitor);
}