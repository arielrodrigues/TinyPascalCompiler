package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum Sign{
	PLUS, MINUS;
	public void accept (PascalVisitor visitor) {
		visitor.VisitSign(this);
	}
}