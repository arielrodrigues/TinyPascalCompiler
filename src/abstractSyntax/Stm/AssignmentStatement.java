package abstractSyntax.Stm;

import abstractSyntax.Exp.Expression;
import abstractSyntax.VariableAccess;

public class AssignmentStatement extends Statement {
	public VariableAccess left;
	public Expression right;
	
	public AssignmentStatement(VariableAccess left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}
}