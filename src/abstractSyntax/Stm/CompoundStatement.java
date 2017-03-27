package abstractSyntax.Stm;

import visitor.PasVisitor;

import java.util.List;

public class CompoundStatement extends Statement {
	public List<Statement> stmts;

	public CompoundStatement(List<Statement> stmts) {
		super();
		this.stmts = stmts;
	}

	@Override
	public void accept(PasVisitor visitor) {
		for (Statement stm : stmts) stm.accept(visitor);
	}
}