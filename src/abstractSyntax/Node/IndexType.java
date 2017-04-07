package abstractSyntax.Node;

import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PascalVisitor;

public class IndexType extends Node {
	public String lowId, highId;
	public TypeIdOrPrimitive rangeTy;
	
	public IndexType(String lowId, String highId, TypeIdOrPrimitive rangeTy) {
		super();
		this.lowId = lowId;
		this.highId = highId;
		this.rangeTy = rangeTy;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitIndexType(this);
	}
}