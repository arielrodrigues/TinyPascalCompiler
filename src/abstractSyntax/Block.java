package abstractSyntax;

import abstractSyntax.Stm.Statement;
import abstractSyntax.labelsAndTypes.UnsignedNumber;
import abstractSyntax.procedureAndFunctionDeclaration.ProcedureOrFunctionDeclaration;
import abstractSyntax.variablesDeclaration.VariableDeclaration;

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
}