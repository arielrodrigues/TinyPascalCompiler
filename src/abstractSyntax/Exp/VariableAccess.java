package abstractSyntax.Exp;

import visitor.PasVisitor;

public abstract class VariableAccess extends Expression {
    public abstract void accept(PasVisitor visitor); }