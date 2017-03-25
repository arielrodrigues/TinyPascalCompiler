package abstractSyntax;

public class MultiDimensionConformant extends ConformantArraySchema {
	public String lowId, highId;
	public TypeIdOrPrimitive rangeTy;
	public ConformantArraySchema elemTy;
	
	public MultiDimensionConformant(String lowId, String highId, TypeIdOrPrimitive rangeTy,
			ConformantArraySchema elemTy) {
		super();
		this.lowId = lowId;
		this.highId = highId;
		this.rangeTy = rangeTy;
		this.elemTy = elemTy;
	}
}