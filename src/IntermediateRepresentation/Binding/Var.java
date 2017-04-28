package IntermediateRepresentation.Binding;

public class Var implements Binding {
	public boolean isByReferene;
	public int frameLoc;
	public Rep rep;
	public int nestingLevel;
	
	public Var(int frameLoc, Rep rep, int nestingLevel) {
		super();
		this.isByReferene = false;
		this.frameLoc = frameLoc;
		this.rep = rep;
		this.nestingLevel = nestingLevel;
	}
	
	public Var(boolean isByReferene, int frameLoc, Rep rep, int nestingLevel) {
		super();
		this.isByReferene = isByReferene;
		this.frameLoc = frameLoc;
		this.rep = rep;
		this.nestingLevel = nestingLevel;
	}
}

