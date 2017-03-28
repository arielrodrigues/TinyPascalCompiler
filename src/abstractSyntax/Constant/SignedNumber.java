package abstractSyntax.Constant;

import abstractSyntax.Operator.Sign;
import visitor.PascalVisitor;

public class SignedNumber extends Constant {
	public Sign sign;
	public UnsignedNumber unsNum;
	
	public SignedNumber(Sign sign, UnsignedNumber unsNum) {
		super();
		this.sign = sign;
		this.unsNum = unsNum;
	}

	public void accept(PascalVisitor visitor) {
		visitor.VisitSignedNumber(this);
	}
	
}