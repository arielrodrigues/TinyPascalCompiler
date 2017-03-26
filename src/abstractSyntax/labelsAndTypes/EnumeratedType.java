package abstractSyntax.labelsAndTypes;

import java.util.List;

public class EnumeratedType extends TypeIdOrOrdinal {
	public List<String> newConstants;

	public EnumeratedType(List<String> newConstants) {
		super();
		this.newConstants = newConstants;
	}
}