package abstractSyntax.Constant;

import visitor.PascalVisitor;

public class UnsignedNumber extends Constant {
  public int value;

  public UnsignedNumber(int value) {
	super();
	this.value = value;
  }

  public void accept(PascalVisitor visitor) {
      visitor.VisitUnsignedNumber(this);
  }

}