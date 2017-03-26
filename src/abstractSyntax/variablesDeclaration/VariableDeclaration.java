package abstractSyntax.variablesDeclaration;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class VariableDeclaration {
  public String id;
  public TypeDenoter ty;
  
  public VariableDeclaration(String id, TypeDenoter ty) {
	super();
	this.id = id;
	this.ty = ty;
  } 
}