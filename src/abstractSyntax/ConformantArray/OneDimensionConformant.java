package abstractSyntax.ConformantArray;

import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PascalVisitor;

public class OneDimensionConformant extends ConformantArraySchema {
	public String lowId, highId;
	public TypeIdOrPrimitive rangeTy;
	public TypeIdOrPrimitive elemTy;
	
	public OneDimensionConformant(String lowId, String highId, TypeIdOrPrimitive rangeTy, TypeIdOrPrimitive elemTy) {
		super();
		this.lowId = lowId;
		this.highId = highId;
		this.rangeTy = rangeTy;
		this.elemTy = elemTy;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitOneDimensionConformant(this);
	}
}