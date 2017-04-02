package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum BinaryBooleanOperator{
	AND, OR;

	public void accept(PascalVisitor visitor) {
		visitor.VisitBinaryBooleanOperator(this);
	}
}