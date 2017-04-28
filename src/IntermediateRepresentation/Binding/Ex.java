package IntermediateRepresentation.Binding;

import IntermediateRepresentation.Temp.Label;
import IntermediateRepresentation.Tree.*;

public class Ex extends Exp {
	Expr exp;

	public Ex(Expr exp) {
		super();
		this.exp = exp;
	}

	@Override
	public Expr unEx() {
		return exp;
	}

	@Override
	public Stm unCx(Label t, Label f) {
		//CJUMP(EQ,1,exp,t,f)
		return new CJUMP(CJUMP.EQ, new CONST(1), exp, t, f) ;
	}

}
