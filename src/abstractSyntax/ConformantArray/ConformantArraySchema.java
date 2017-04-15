package abstractSyntax.ConformantArray;

import visitor.PascalVisitor;

public abstract class ConformantArraySchema {
    public abstract Object accept(PascalVisitor visitor);
}