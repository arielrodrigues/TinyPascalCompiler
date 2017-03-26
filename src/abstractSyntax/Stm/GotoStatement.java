package abstractSyntax.Stm;

import abstractSyntax.Constant.UnsignedNumber;

public class GotoStatement extends Statement {
	public UnsignedNumber label;

	public GotoStatement(UnsignedNumber label) {
		super();
		this.label = label;
	}
}