package abstractSyntax.Constant;

import abstractSyntax.Sign;

public class SignedNumber extends Constant {
	Sign sign;
	UnsignedNumber unsNum;
	
	public SignedNumber(Sign sign, UnsignedNumber unsNum) {
		super();
		this.sign = sign;
		this.unsNum = unsNum;
	}	
	
}