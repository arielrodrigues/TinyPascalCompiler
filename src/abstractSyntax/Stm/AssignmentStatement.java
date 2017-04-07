package abstractSyntax.Stm;

import abstractSyntax.Exp.Expression;
import abstractSyntax.variablesDeclaration.VariableAccess;
import visitor.PascalVisitor;

public class AssignmentStatement extends Statement {
	public VariableAccess left;
	public Expression right;
	
	public AssignmentStatement(VariableAccess left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitAssignStm(this);
	}
}