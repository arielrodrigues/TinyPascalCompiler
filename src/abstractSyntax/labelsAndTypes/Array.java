package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public class Array extends TypeDenoter {
	public TypeIdOrOrdinal range;
	public TypeDenoter elemTy;
	
	public Array(TypeIdOrOrdinal range, TypeDenoter elemTy) {
		super();
		this.range = range;
		this.elemTy = elemTy;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitArray(this);
	}
}