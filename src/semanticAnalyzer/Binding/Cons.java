package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Cons implements Binding {
	public TypeDenoter type;
	public int value;

	public Cons(TypeDenoter type, int value) {
		super();
		this.type = type;
		this.value = value;
	}

}
