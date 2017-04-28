package IntermediateRepresentation.Binding;

import IntermediateRepresentation.Tree.*;
import IntermediateRepresentation.Temp.*;

public abstract class Cx extends Exp {

	@Override
	public Expr unEx() {
		/* 
		 *(     unCx(t,f)
		 *   f: MOVE(TEMP(r), CONST(0))
		 *      JUMP(fim)
		 *   t: MOVE(TEMP(r), CONST(1))
		 *   fim: NOP,
		 * TEMP(r) )  
		 */
		Label t = new Label();
		Label f = new Label();
		Label fim = new Label();
		Temp r = new Temp();
		return new ESEQ(
				  new SEQ(
				     unCx(t,f),
				     new SEQ(
			            new LABEL(f),
			            new SEQ(
			               new MOVE(new TEMP(r), new CONST(0)),
			               new SEQ (
			                  new JUMP(fim),
			                  new SEQ (
			                     new LABEL(t),
			                     new SEQ (
			            	        new MOVE(new TEMP(r), new CONST(1)),
			            	        new LABEL(fim) ) ) ) ) ) ),
				  new TEMP(r) );
	}

	@Override
	public abstract Stm unCx(Label t, Label f);
}
