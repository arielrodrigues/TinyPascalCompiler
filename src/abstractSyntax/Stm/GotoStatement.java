package abstractSyntax.Stm;

import abstractSyntax.labelsAndTypes.UnsignedNumber;

public class GotoStatement extends Statement {
	public UnsignedNumber label;

	public GotoStatement(UnsignedNumber label) {
		super();
		this.label = label;
	}
}