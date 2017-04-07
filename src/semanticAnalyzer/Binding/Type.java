package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Type implements Binding {
	TypeDenoter type;

	public Type(TypeDenoter type) {
		super();
		this.type = type;
	}

}
