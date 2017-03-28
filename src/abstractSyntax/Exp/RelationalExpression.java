package abstractSyntax.Exp;

import abstractSyntax.Operator.RelationalOperator;
import visitor.PascalVisitor;

public class RelationalExpression extends Expression {
	public RelationalOperator op;
	public Expression left, right;
	
	public RelationalExpression(RelationalOperator op, Expression left, Expression right) {
		super();
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		left.accept(visitor);
		op.accept(visitor);
		right.accept(visitor);
	}
}