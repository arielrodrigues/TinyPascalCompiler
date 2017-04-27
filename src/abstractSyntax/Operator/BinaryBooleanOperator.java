package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum BinaryBooleanOperator{
	AND, OR;

	public Object accept(PascalVisitor visitor) {
		return visitor.VisitBinaryBooleanOperator(this);
	}
}