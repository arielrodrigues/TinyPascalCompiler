package abstractSyntax.Exp;

import visitor.PasVisitor;

public class IndexedVariable extends VariableAccess {
	public VariableAccess var;
	public Expression index;
	
	public IndexedVariable(VariableAccess var, Expression index) {
		super();
		this.var = var;
		this.index = index;
	}

	@Override
	public void accept(PasVisitor visitor) {
		var.accept(visitor);
		index.accept(visitor);
	}
}