package IntermediateRepresentation.Tree;

import IntermediateRepresentation.Temp.Label;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JUMP extends Stm {
  public Expr exp;
  public List<Label> targets;
  public JUMP(Expr e, List<Label> t) {exp=e; targets=t;}
  public JUMP(Label target) {
      this(new NAME(target),  new LinkedList<>(Collections.singletonList(target)));
  }
  /*
  public ExpList kids() {return new ExpList(exp,null);}
  public Stm build(ExpList kids) {
    return new JUMP(kids.head,targets);
  }
  */
}

