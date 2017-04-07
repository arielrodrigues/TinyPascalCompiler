package abstractSyntax.FormalParameter;

import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PascalVisitor;

public class FormalPar extends FormalParameter {
	public RefOrValue mechanism;
	public String name;
	public TypeIdOrPrimitive type;
	
	public FormalPar(RefOrValue mechanism, String name, TypeIdOrPrimitive type) {
		super();
		this.mechanism = mechanism;
		this.name = name;
		this.type = type;
	}

    @Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitFormalPar(this);
	}
}