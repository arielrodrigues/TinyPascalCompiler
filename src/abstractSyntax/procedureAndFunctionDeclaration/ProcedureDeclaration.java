package abstractSyntax.procedureAndFunctionDeclaration;

import abstractSyntax.Node.Block;
import abstractSyntax.FormalParameter.FormalParameter;
import visitor.PascalVisitor;

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

	@Override
	public void accept(PascalVisitor visitor) {
		for (FormalParameter formal : formals) formal.accept(visitor);
		body.accept(visitor);
	}
}