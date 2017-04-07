package abstractSyntax.labelsAndTypes;

import abstractSyntax.Constant.Constant;
import visitor.PascalVisitor;

public class SubrangeType extends TypeIdOrOrdinal {
	public Constant low, high;

	public SubrangeType(Constant low, Constant high) {
		super();
		this.low = low;
		this.high = high;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitSubRangeType(this);
	}
}