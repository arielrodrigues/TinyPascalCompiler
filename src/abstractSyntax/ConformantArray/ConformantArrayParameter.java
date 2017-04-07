package abstractSyntax.ConformantArray;

import abstractSyntax.FormalParameter.FormalParameter;
import abstractSyntax.FormalParameter.RefOrValue;
import visitor.PascalVisitor;

public class ConformantArrayParameter extends FormalParameter {
	public RefOrValue mechanism;
	public String name;
	public ConformantArraySchema schema;
	
	public ConformantArrayParameter(RefOrValue mechanism, String name, ConformantArraySchema schema) {
		super();
		this.mechanism = mechanism;
		this.name = name;
		this.schema = schema;
	}

	public Object accept(PascalVisitor visitor) {
		return visitor.VisitConformantArrayParameter(this);
	}
}