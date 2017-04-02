package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class NotExpression extends Expression {
	public Expression exp;

	public NotExpression(Expression exp) {
		super();
		this.exp = exp;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitNotExpression(this);
	}
}