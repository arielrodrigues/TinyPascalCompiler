package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum BinaryArithmeticOperator {
	DIV, MOD, PLUS, TIMES,  MINUS;

	public Object accept(PascalVisitor visitor) {
		return visitor.VisitBinaryArithmeticOperator(this);
	}
}