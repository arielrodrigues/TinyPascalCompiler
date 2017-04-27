package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum Sign{
	PLUS, MINUS;
	public Object accept (PascalVisitor visitor) {
		return visitor.VisitSign(this);
	}
}