package abstractSyntax.Exp;

import visitor.PascalVisitor;

public abstract class VariableAccess extends Expression {
    public abstract void accept(PascalVisitor visitor); }