package semanticAnalyzer.Binding;

import abstractSyntax.FormalParameter.RefOrValue;
import abstractSyntax.labelsAndTypes.Array;

public class ConformantParameter extends Parameter {
	public Array type;

	public ConformantParameter(RefOrValue mechanism, Array type) {
		super(mechanism);
		this.type = type;
	}
	
}
