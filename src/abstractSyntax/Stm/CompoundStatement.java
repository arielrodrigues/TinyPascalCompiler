package abstractSyntax.Stm;

import visitor.PascalVisitor;

import java.util.List;

public class CompoundStatement extends Statement {
	public List<Statement> stmts;

	public CompoundStatement(List<Statement> stmts) {
		super();
		this.stmts = stmts;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitCompStm(this);
	}
}