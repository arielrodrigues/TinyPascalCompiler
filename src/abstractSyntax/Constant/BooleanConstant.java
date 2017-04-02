package abstractSyntax.Constant;

import visitor.PascalVisitor;

public class BooleanConstant extends Constant {
	public static final BooleanConstant TRUE = new BooleanConstant(true);
	public static final BooleanConstant FALSE = new BooleanConstant(false);
	
	public boolean value;

	private BooleanConstant(boolean value) {
		super();
		this.value = value;
	}

	public void accept(PascalVisitor visitor) {
		visitor.VisitBooleanConstant(this);
	}
}