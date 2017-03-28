package abstractSyntax.FormalParameter;

import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PascalVisitor;

public class FormalVar extends FormalParameter {
	public String name;
	public TypeIdOrPrimitive type;
	
	public FormalVar(String name, TypeIdOrPrimitive type) {
		super();
		this.name = name;
		this.type = type;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		type.accept(visitor);
	}
}