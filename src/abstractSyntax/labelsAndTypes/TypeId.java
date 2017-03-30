package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public class TypeId extends TypeIdOrOrdinal {
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