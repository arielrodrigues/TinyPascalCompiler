package abstractSyntax.FormalParameter;

import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PasVisitor;

public class FormalRef extends FormalParameter {
	public String name;
	public TypeIdOrPrimitive type;
	
	public FormalRef(String name, TypeIdOrPrimitive type) {
		super();
		this.name = name;
		this.type = type;
	}

	@Override
	public void accept(PasVisitor visitor) {
		type.accept(visitor);
	}
}