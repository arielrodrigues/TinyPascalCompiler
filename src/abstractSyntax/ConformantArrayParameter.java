package abstractSyntax;

public class ConformantArrayParameter {
	public RefOrValue mechanism;
	public String name;
	public ConformantArraySchema schema;
	
	public ConformantArrayParameter(RefOrValue mechanism, String name, ConformantArraySchema schema) {
		super();
		this.mechanism = mechanism;
		this.name = name;
		this.schema = schema;
	}
}