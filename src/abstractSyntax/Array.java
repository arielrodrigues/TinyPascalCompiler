package abstractSyntax;

public class Array extends  TypeDenoter {
	TypeIdOrOrdinal range;
	TypeDenoter elemTy;	
	
	public Array(TypeIdOrOrdinal range, TypeDenoter elemTy) {
		super();
		this.range = range;
		this.elemTy = elemTy;
	}

}