package abstractSyntax.Exp;

import abstractSyntax.Operator.BinaryBooleanOperator;
import visitor.PascalVisitor;

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
	public void accept(PascalVisitor visitor) {
		visitor.VisitBinaryBooleanExpression(this);
	}
}