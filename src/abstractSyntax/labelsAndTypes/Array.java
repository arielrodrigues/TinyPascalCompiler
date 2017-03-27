package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

public class Array extends TypeDenoter {
	TypeIdOrOrdinal range;
	TypeDenoter elemTy;	
	
	public Array(TypeIdOrOrdinal range, TypeDenoter elemTy) {
		super();
		this.range = range;
		this.elemTy = elemTy;
	}

	@Override
	public void accept(PasVisitor visitor) {
		range.accept(visitor);
		elemTy.accept(visitor);
	}
}