package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Var implements Binding {
	public TypeDenoter type;

	public Var(TypeDenoter type) {
		super();
		this.type = type;
	}

}
