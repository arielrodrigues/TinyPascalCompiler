package abstractSyntax.Constant;

import abstractSyntax.Operator.Sign;
import visitor.PasVisitor;

public class SignedNumber extends Constant {
	Sign sign;
	UnsignedNumber unsNum;
	
	public SignedNumber(Sign sign, UnsignedNumber unsNum) {
		super();
		this.sign = sign;
		this.unsNum = unsNum;
	}

	public void accept(PasVisitor visitor) {
		sign.accept(visitor);
		unsNum.accept(visitor);
	}
	
}