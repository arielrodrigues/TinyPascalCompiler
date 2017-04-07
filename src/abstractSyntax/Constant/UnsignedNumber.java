package abstractSyntax.Constant;

import visitor.PascalVisitor;

public class UnsignedNumber extends Constant {
  public int value;

  public UnsignedNumber(int value) {
	super();
	this.value = value;
  }

  public Object accept(PascalVisitor visitor) {
      return visitor.VisitUnsignedNumber(this);
  }

}