package abstractSyntax.Exp;

import abstractSyntax.Operator.BinaryBooleanOperator;
import visitor.PasVisitor;

public class BinaryBooleanExpression extends Expression {
	public BinaryBooleanOperator op;
	public Expression left, right;
	
	public BinaryBooleanExpression(BinaryBooleanOperator op, Expression left, Expression right) {
		super();
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(PasVisitor visitor) {
		left.accept(visitor);
		op.accept(visitor);
		right.accept(visitor);
	}
}