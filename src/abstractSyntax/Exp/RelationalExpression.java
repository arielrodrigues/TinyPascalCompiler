package abstractSyntax.Exp;

import abstractSyntax.Operator.RelationalOperator;
import visitor.PascalVisitor;

public class RelationalExpression extends Expression {
	public RelationalOperator op;
	public Expression left, right;
	
	public RelationalExpression(RelationalOperator op, Expression left, Expression right) {
		super();
		this.op = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitRelationalExpression(this);
	}
}