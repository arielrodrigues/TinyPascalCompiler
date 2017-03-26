package abstractSyntax.labelsAndTypes;

import abstractSyntax.Constant.Constant;

public class SubrangeType extends TypeIdOrOrdinal {
	Constant low, high;

	public SubrangeType(Constant low, Constant high) {
		super();
		this.low = low;
		this.high = high;
	}
	
}