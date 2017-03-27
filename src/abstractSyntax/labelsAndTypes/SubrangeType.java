package abstractSyntax.labelsAndTypes;

import abstractSyntax.Constant.Constant;
import visitor.PasVisitor;

public class SubrangeType extends TypeIdOrOrdinal {
	Constant low, high;

	public SubrangeType(Constant low, Constant high) {
		super();
		this.low = low;
		this.high = high;
	}

	@Override
	public void accept(PasVisitor visitor) {
		low.accept(visitor);
		high.accept(visitor);
	}
}