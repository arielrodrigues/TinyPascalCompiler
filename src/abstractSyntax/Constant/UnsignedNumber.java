package abstractSyntax.Constant;

import visitor.PasVisitor;

public class UnsignedNumber extends Constant {
  public int value;

  public UnsignedNumber(int value) {
	super();
	this.value = value;
  }

  public void accept(PasVisitor visitor) {
      // nothing to do here
  }
}