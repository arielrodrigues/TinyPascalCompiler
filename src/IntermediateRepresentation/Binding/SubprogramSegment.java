package IntermediateRepresentation.Binding;

import IntermediateRepresentation.Temp.Label;
import IntermediateRepresentation.Tree.Stm;

public class SubprogramSegment implements Binding {
	public Label label;
	public Frame frame;
	public Stm body;
	
	public SubprogramSegment(Label label, Frame frame, Stm body) {
		super();
		this.label = label;
		this.frame = frame;
		this.body = body;
	}
}
