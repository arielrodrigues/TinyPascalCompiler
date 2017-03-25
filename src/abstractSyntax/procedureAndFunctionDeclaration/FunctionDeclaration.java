package abstractSyntax.procedureAndFunctionDeclaration;

import abstractSyntax.Block;
import abstractSyntax.FormalParameter;
import abstractSyntax.TypeIdOrPrimitive;

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
}