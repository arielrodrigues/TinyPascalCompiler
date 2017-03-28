package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum RelationalOperator {
	EQ, NEQ, LT, GT, LTE, GTE;

	public void accept (PascalVisitor visitor) {
		visitor.VisitRelationalOperator(this);
	}
}