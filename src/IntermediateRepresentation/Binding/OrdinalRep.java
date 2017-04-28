package IntermediateRepresentation.Binding;

public class OrdinalRep extends Rep {
	public static int minInt = Integer.MIN_VALUE;
	public static int maxInt = Integer.MAX_VALUE;
	public static int minChar = 0;
	public static int maxChar = 255;
	public static int minBool = 0;
	public static int maxBool = 1;
	
	public static final OrdinalRep INTEGER_REP = new OrdinalRep(minInt, maxInt);
	public static final OrdinalRep CHAR_REP = new OrdinalRep(minChar, maxChar);
	public static final OrdinalRep BOOLEAN_REP = new OrdinalRep(minBool, maxBool);
	
	public int lower, upper;
	
	public OrdinalRep(int lower, int upper) {
		super();
		this.lower = lower;
		this.upper = upper;
	}


	@Override
	public int size() {
		return 1;
	}
}