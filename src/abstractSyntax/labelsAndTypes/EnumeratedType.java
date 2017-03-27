package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

import java.util.List;

public class EnumeratedType extends TypeIdOrOrdinal {
	public List<String> newConstants;

	public EnumeratedType(List<String> newConstants) {
		super();
		this.newConstants = newConstants;
	}

	@Override
	public void accept(PasVisitor visitor) {
		// nothing to do here
	}
}