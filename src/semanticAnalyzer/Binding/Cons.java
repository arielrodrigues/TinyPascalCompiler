package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Cons implements Binding {
	public TypeDenoter type;

	public Cons(TypeDenoter type) {
		super();
		this.type = type;
	}

}
