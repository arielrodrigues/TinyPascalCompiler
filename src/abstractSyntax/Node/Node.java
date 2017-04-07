package abstractSyntax.Node;

import visitor.PascalVisitor;

public abstract class Node {
    public abstract Object accept(PascalVisitor visitor);
}
