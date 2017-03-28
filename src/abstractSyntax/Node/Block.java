package abstractSyntax.Node;

import abstractSyntax.Constant.*;
import abstractSyntax.Stm.Statement;
import abstractSyntax.labelsAndTypes.TypeDefinition;
import abstractSyntax.procedureAndFunctionDeclaration.ProcedureOrFunctionDeclaration;
import abstractSyntax.variablesDeclaration.VariableDeclaration;
import visitor.PascalVisitor;

import java.util.List;

public class Block extends Node{
	public List<UnsignedNumber> labels;
	public List<TypeDefinition> typeDefs;
	public List<VariableDeclaration> varDecs;
	public List<ProcedureOrFunctionDeclaration> subprogs;
	public Statement body;
	
	public Block(List<UnsignedNumber> labels, List<TypeDefinition> typeDefs, List<VariableDeclaration> varDecs,
			List<ProcedureOrFunctionDeclaration> subprogs, Statement body) {
		super();
		this.labels = labels;
		this.typeDefs = typeDefs;
		this.varDecs = varDecs;
		this.subprogs = subprogs;
		this.body = body;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitBlock(this);
	}
}