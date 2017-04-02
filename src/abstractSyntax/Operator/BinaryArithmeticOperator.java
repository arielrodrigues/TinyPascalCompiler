package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum BinaryArithmeticOperator {
	DIV, MOD, PLUS, TIMES,  MINUS;

	public void accept(PascalVisitor visitor) {
		visitor.VisitBinaryArithmeticOperator(this);
	}
}