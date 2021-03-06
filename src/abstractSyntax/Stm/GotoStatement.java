package abstractSyntax.Stm;

import abstractSyntax.Constant.UnsignedNumber;
import visitor.PascalVisitor;

public class GotoStatement extends Statement {
	public UnsignedNumber label;

	public GotoStatement(UnsignedNumber label) {
		super();
		this.label = label;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitGotoStatement(this);
	}
}