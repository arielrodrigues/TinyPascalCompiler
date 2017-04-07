package abstractSyntax.FormalParameter;

import abstractSyntax.Node.Node;
import visitor.PascalVisitor;

public abstract class FormalParameter extends Node {
    public abstract Object accept(PascalVisitor visitor);
}