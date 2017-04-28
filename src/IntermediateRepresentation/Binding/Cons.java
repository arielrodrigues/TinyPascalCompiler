package IntermediateRepresentation.Binding;

//Assuming that constant just can denote ordinals. 
public class Cons implements Binding {
	public int ord;

	public Cons(int ord) {
		super();
		this.ord = ord;
	}
}
