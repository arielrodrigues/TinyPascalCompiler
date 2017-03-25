package abstractSyntax.Stm;

import abstractSyntax.Exp.Expression;

import java.util.List;

public class ProcedureStatement extends Statement {
	public String name;
	public List<Expression> actuals;
	
	public ProcedureStatement(String name, List<Expression> actuals) {
		super();
		this.name = name;
		this.actuals = actuals;
	}
}