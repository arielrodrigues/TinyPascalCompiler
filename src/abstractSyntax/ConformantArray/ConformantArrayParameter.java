package abstractSyntax.ConformantArray;

import abstractSyntax.FormalParameter.RefOrValue;
import visitor.PrettyprintVisitor;

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

	public void prettyPrint(PrettyprintVisitor prettyprintVisitor) {
		mechanism.prettyPrint(prettyprintVisitor);
		System.out.prettyPrint(name);
		schema.prettyPrint(prettyprintVisitor);
	}
}