package abstractSyntax.Stm;

import abstractSyntax.Exp.Expression;
import visitor.PascalVisitor;

import java.util.List;

public class ProcedureStatement extends Statement {
	public String name;
	public List<Expression> actuals;
	
	public ProcedureStatement(String name, List<Expression> actuals) {
		super();
		this.name = name;
		this.actuals = actuals;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitProcedureStm(this);
	}
}