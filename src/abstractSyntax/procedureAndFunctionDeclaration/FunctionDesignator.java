package abstractSyntax.procedureAndFunctionDeclaration;

import abstractSyntax.Exp.Expression;
import visitor.PascalVisitor;

import java.util.List;

public class FunctionDesignator extends Expression {
	public String name;
	public List<Expression> actuals;
	
	public FunctionDesignator(String name, List<Expression> actuals) {
		super();
		this.name = name;
		this.actuals = actuals;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitFunctionDesignator(this);
	}
}