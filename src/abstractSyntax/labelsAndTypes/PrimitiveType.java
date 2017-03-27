package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

public class PrimitiveType extends TypeDenoter {

	public static PrimitiveType INTEGER = new PrimitiveType();
	public static PrimitiveType CHAR = new PrimitiveType();
	public static PrimitiveType STRING = new PrimitiveType();
	public static PrimitiveType BOOLEAN = new PrimitiveType();
	
	private PrimitiveType() {}

	@Override
	public void accept(PasVisitor visitor) {
		// nothing to do here (?)
	}
}