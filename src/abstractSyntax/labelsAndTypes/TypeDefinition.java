package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public class TypeDefinition {
	
	public String id;
	public TypeDenoter ty;
	
	public TypeDefinition(String id, TypeDenoter ty) {
		super();
		this.id = id;
		this.ty = ty;
	}

	public Object accept(PascalVisitor visitor) {
		return visitor.VisitTypeDefinition(this);
	}
	
}
