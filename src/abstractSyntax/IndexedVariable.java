package abstractSyntax;

import abstractSyntax.Exp.Expression;

public class IndexedVariable extends VariableAccess {
	public VariableAccess var;
	public Expression index;
	
	public IndexedVariable(VariableAccess var, Expression index) {
		super();
		this.var = var;
		this.index = index;
	}
}