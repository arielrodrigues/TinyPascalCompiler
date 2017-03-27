package abstractSyntax.Node;

import visitor.PasVisitor;

public abstract class Node {
    public abstract void accept(PasVisitor visitor);
}
