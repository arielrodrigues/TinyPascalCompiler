package abstractSyntax.Node;

import abstractSyntax.Stm.Statement;
import abstractSyntax.Constant.UnsignedNumber;
import abstractSyntax.labelsAndTypes.TypeDefinition;
import abstractSyntax.procedureAndFunctionDeclaration.ProcedureOrFunctionDeclaration;
import abstractSyntax.variablesDeclaration.VariableDeclaration;
import visitor.PasVisitor;

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
	public void accept(PasVisitor visitor) {
		for (UnsignedNumber label : labels) label.accept(visitor);
		for (TypeDefinition typeDef : typeDefs) typeDef.accept(visitor);
		for (VariableDeclaration varDec : varDecs) varDec.accept(visitor);
		for (ProcedureOrFunctionDeclaration subprog : subprogs) subprog.accept(visitor);
		body.accept(visitor);
	}
}