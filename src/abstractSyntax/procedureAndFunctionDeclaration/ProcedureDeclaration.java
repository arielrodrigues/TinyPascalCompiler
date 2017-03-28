package abstractSyntax.procedureAndFunctionDeclaration;

import abstractSyntax.Block;
import abstractSyntax.FormalParameter;

import java.util.List;

public class ProcedureDeclaration extends ProcedureOrFunctionDeclaration {
	public String nm;
	public List<FormalParameter> formals;
	public Block body;
	
	public ProcedureDeclaration(String nm, List<FormalParameter> formals, Block body) {
		super();
		this.nm = nm;
		this.formals = formals;
		this.body = body;
	}
}