package abstractSyntax.variablesDeclaration;

import abstractSyntax.Exp.Expression;
import visitor.PascalVisitor;

public abstract class VariableAccess extends Expression {
    public abstract void accept(PascalVisitor visitor); }