package IntermediateRepresentation.Binding;

public class Frame {
	// Loc 0 is reserved for the static link
	private int nextLoc = 1;

	public int staticLink() {
		return 0;
	}
	
	// Allocates a block of n words and returns the initial loc of the block
	public int alloc(int n) {
		int initial = nextLoc;
		nextLoc += n;
		return initial;
	}

	public int size() {
		return nextLoc;
	}
}
