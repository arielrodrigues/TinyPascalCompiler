package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public class TypeId extends TypeIdOrOrdinal implements TypeIdOrPrimitive {
	public String id;

	public TypeId(String id) {
		super();
		this.id = id;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitTypeId(this);
	}
}