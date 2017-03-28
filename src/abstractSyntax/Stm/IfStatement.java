package abstractSyntax.Stm;

import abstractSyntax.Exp.Expression;

public class IfStatement extends Statement {
	public Expression condition;
	public Statement thenPart, elsePart;
	
	public IfStatement(Expression condition, Statement thenPart, Statement elsePart) {
		super();
		this.condition = condition;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
}