package IntermediateRepresentation.Binding;

public class ArrayRep extends Rep {
	public int lowerBound, upperBound;
	public Rep elemRep;
	
	public ArrayRep(int lowerBound, int upperBound, Rep elemRep) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.elemRep = elemRep;
	}

	@Override
	public int size() {
		return (upperBound - lowerBound + 1) * elemRep.size();
	}

}
