package semanticAnalyzer.Binding;

import java.util.List;
import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;

public class Function implements Binding {
	List<Parameter> parameters;
	TypeIdOrPrimitive retTy;
	public Function(List<Parameter> parameters, TypeIdOrPrimitive retTy) {
		super();
		this.parameters = parameters;
		this.retTy = retTy;
	}
}
