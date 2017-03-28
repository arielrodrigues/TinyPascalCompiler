package abstractSyntax.Exp;

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
	public void accept(PascalVisitor visitor) {
		visitor.VisitIndexedVariable(this);
	}
}