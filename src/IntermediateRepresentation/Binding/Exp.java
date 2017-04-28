package IntermediateRepresentation.Binding;

import IntermediateRepresentation.Temp.Label;
import IntermediateRepresentation.Tree.*;

public abstract class Exp {
	public abstract Expr unEx();
	public abstract Stm unCx(Label t, Label f);
}
