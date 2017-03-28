package abstractSyntax.variablesDeclaration;

import abstractSyntax.labelsAndTypes.TypeDenoter;
import visitor.PascalVisitor;

public class VariableDeclaration {
  public String id;
  public TypeDenoter ty;
  
  public VariableDeclaration(String id, TypeDenoter ty) {
	super();
	this.id = id;
	this.ty = ty;
  }

  public void accept (PascalVisitor visitor) {
       visitor.VisitVariableDeclaration(this);
  }
}