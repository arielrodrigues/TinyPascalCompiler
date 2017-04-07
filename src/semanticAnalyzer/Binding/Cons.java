package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Cons implements Binding {
	TypeDenoter type;

	public Cons(TypeDenoter type) {
		super();
		this.type = type;
	}

}
