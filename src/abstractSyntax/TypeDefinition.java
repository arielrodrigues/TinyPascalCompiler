package abstractSyntax;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class TypeDefinition {
	
	String id;
	TypeDenoter ty;
	
	public TypeDefinition(String id, TypeDenoter ty) {
		super();
		this.id = id;
		this.ty = ty;
	}
	
}
