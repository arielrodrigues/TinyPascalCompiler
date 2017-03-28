package abstractSyntax.Stm;

import abstractSyntax.Exp.Expression;

class WhileStatement extends Statement {
	public Expression condition;
	public Statement body;
	
	public WhileStatement(Expression condition, Statement body) {
		super();
		this.condition = condition;
		this.body = body;
	}
}