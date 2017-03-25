package abstractSyntax.Stm;

import abstractSyntax.Stm.Statement;

import java.util.List;

public class CompoundStatement extends Statement {
	public List<Statement> stmts;

	public CompoundStatement(List<Statement> stmts) {
		super();
		this.stmts = stmts;
	}
}