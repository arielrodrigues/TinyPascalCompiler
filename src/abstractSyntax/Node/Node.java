package abstractSyntax.Node;

import visitor.PascalVisitor;

public abstract class Node {
    public abstract void accept(PascalVisitor visitor);
}
