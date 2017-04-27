package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum RelationalOperator {
	EQ, NEQ, LT, GT, LTE, GTE;

	public Object accept (PascalVisitor visitor) {
		return visitor.VisitRelationalOperator(this);
	}
}