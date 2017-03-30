package abstractSyntax.ConformantArray;

import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PascalVisitor;
import visitor.PrettyprintVisitor;

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

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitMultiDimensionConformant(this);
	}
}