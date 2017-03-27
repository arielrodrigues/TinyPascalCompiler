package abstractSyntax.procedureAndFunctionDeclaration;

import abstractSyntax.Node.Block;
import abstractSyntax.FormalParameter.FormalParameter;
import abstractSyntax.labelsAndTypes.TypeIdOrPrimitive;
import visitor.PasVisitor;

import java.util.List;

public class FunctionDeclaration extends ProcedureOrFunctionDeclaration {
	public String nm;
	public List<FormalParameter> formals;
	public TypeIdOrPrimitive resultTy;
	public Block body;
	
	public FunctionDeclaration(String nm, List<FormalParameter> formals, TypeIdOrPrimitive resultTy, Block body) {
		super();
		this.nm = nm;
		this.formals = formals;
		this.resultTy = resultTy;
		this.body = body;
	}

	@Override
	public void accept(PasVisitor visitor) {
		for (FormalParameter formal : formals) formal.accept(visitor);
		resultTy.accept(visitor);
		body.accept(visitor);
	}
}