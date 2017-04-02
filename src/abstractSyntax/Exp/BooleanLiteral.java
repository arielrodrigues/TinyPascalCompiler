package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class BooleanLiteral extends Expression {
	public boolean value;

	public static final BooleanLiteral TRUE = new BooleanLiteral(true);
	public static final BooleanLiteral FALSE = new BooleanLiteral(false);
	
	private BooleanLiteral(boolean value) {
		super();
		this.value = value;
	}

	public void accept(PascalVisitor visitor) {
		visitor.VisitBooleanLiteral(this);
	}	
}