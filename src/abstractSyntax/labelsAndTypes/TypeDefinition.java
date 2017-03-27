package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

public class TypeDefinition {
	
	String id;
	TypeDenoter ty;
	
	public TypeDefinition(String id, TypeDenoter ty) {
		super();
		this.id = id;
		this.ty = ty;
	}

	public void accept(PasVisitor visitor) {
		ty.accept(visitor);
	}
	
}
