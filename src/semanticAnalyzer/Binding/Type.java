package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Type implements Binding {
	public TypeDenoter type;

	public Type(TypeDenoter type) {
		super();
		this.type = type;
	}

}
