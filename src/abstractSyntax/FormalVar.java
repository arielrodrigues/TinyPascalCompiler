package abstractSyntax;

public class FormalVar extends FormalParameter {
	public String name;
	public TypeIdOrPrimitive type;
	
	public FormalVar(String name, TypeIdOrPrimitive type) {
		super();
		this.name = name;
		this.type = type;
	}
}