package abstractSyntax.variablesDeclaration;

import abstractSyntax.Exp.Expression;
import abstractSyntax.variablesDeclaration.VariableAccess;
import visitor.PascalVisitor;

public class IndexedVariable extends VariableAccess {
	public VariableAccess var;
	public Expression index;
	
	public IndexedVariable(VariableAccess var, Expression index) {
		super();
		this.var = var;
		this.index = index;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitIndexedVariable(this);
	}
}