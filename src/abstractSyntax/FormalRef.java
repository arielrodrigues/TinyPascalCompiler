package abstractSyntax;

public class FormalRef extends FormalParameter {
	public String name;
	public TypeIdOrPrimitive type;
	
	public FormalRef(String name, TypeIdOrPrimitive type) {
		super();
		this.name = name;
		this.type = type;
	}
}