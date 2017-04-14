package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public class IdType extends TypeDenoter implements TypeIdOrPrimitive {
	public String id;

	public IdType(String id) {
		super();
		this.id = id;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitTypeId(this);
	}
}