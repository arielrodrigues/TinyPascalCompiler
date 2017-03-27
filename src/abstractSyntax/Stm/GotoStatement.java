package abstractSyntax.Stm;

import abstractSyntax.Constant.UnsignedNumber;
import visitor.PasVisitor;

public class GotoStatement extends Statement {
	public UnsignedNumber label;

	public GotoStatement(UnsignedNumber label) {
		super();
		this.label = label;
	}

	@Override
	public void accept(PasVisitor visitor) {
		// nothing to do here
	}
}