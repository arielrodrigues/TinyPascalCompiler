package abstractSyntax.Exp;

import visitor.PasVisitor;

public class NotExpression extends Expression {
	public Expression exp;

	public NotExpression(Expression exp) {
		super();
		this.exp = exp;
	}

	@Override
	public void accept(PasVisitor visitor) {
		exp.accept(visitor);
	}
}