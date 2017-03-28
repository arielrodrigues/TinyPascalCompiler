package abstractSyntax.Exp;

import abstractSyntax.Sign;
import abstractSyntax.labelsAndTypes.UnsignedNumber;
import abstractSyntax.labelsAndTypes.Constant;

public class SignedNumber extends Constant {
	Sign sign;
	UnsignedNumber unsNum;
	
	public SignedNumber(Sign sign, UnsignedNumber unsNum) {
		super();
		this.sign = sign;
		this.unsNum = unsNum;
	}	
	
}