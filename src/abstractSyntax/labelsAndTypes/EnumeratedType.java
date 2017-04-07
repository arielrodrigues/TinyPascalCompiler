package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

import java.util.List;

public class EnumeratedType extends TypeIdOrOrdinal {
	public List<String> newConstants;

	public EnumeratedType(List<String> newConstants) {
		super();
		this.newConstants = newConstants;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitEnumeratedType(this);
	}
}