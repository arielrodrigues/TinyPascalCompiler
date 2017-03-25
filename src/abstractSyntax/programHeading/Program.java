package abstractSyntax.programHeading;

import abstractSyntax.Block;
import abstractSyntax.Node;

import java.util.List;

public class Program extends Node {
	public String id;
	public List<String> io;
	public Block block;
	
	public Program(String id, List<String> io, Block block) {
		super();
		this.id = id;
		this.io = io;
		this.block = block;
	}
}