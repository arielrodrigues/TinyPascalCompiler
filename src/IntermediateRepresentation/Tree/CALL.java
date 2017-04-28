package IntermediateRepresentation.Tree;

import java.util.List;

public class CALL extends Expr {
  public Expr func;
  public List<Expr> args;
  public CALL(Expr f, List<Expr> a) {func=f; args=a;}
  /*
  public ExpList kids() {return new ExpList(func,args);}
  public Exp build(ExpList kids) {
    return new CALL(kids.head,kids.tail);
  }
  */
}

