package abstractSyntax.Exp;

import abstractSyntax.Operator.BinaryArithmeticOperator;
import visitor.PascalVisitor;

public class BinaryArithmeticExpression extends Expression {
	public BinaryArithmeticOperator op;
	public Expression left, right;
	
	public BinaryArithmeticExpression(BinaryArithmeticOperator op, Expression left, Expression right) {
		super();
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitBinaryArithmeticExpression(this);
	}
}