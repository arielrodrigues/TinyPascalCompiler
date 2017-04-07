package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public class PrimitiveType extends TypeDenoter implements TypeIdOrPrimitive {

	public static PrimitiveType INTEGER = new PrimitiveType();
	public static PrimitiveType CHAR = new PrimitiveType();
	public static PrimitiveType STRING = new PrimitiveType();
	public static PrimitiveType BOOLEAN = new PrimitiveType();
	
	private PrimitiveType() {}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitPrimitiveType(this);
	}
}