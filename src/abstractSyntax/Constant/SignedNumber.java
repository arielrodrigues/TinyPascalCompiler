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

	public Object accept(PascalVisitor visitor) {
		return visitor.VisitSignedNumber(this);
	}
	
}