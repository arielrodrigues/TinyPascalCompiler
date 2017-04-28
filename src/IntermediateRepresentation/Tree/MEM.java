package IntermediateRepresentation.Tree;

public class MEM extends Expr {
  public Expr exp;
  public MEM(Expr e) {exp=e;}
  /*
  public ExpList kids() {return new ExpList(exp,null);}
  public Exp build(ExpList kids) {
    return new MEM(kids.head);
  }
  */
}

