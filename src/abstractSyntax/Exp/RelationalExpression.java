package abstractSyntax.Exp;

import abstractSyntax.Operator.RelationalOperator;

public class RelationalExpression extends Expression {
	public RelationalOperator op;
	public Expression left, right;
	
	public RelationalExpression(RelationalOperator op, Expression left, Expression right) {
		super();
		this.op = op;
		this.left = left;
		this.right = right;
	}
}