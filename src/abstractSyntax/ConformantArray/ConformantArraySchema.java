package abstractSyntax.ConformantArray;

import visitor.PascalVisitor;

public abstract class ConformantArraySchema {
    public abstract void accept(PascalVisitor visitor);
}