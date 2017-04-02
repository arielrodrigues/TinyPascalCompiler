package abstractSyntax.ConformantArray;

import visitor.PascalVisitor;
import visitor.PrettyprintVisitor;

public abstract class ConformantArraySchema {
    public abstract void accept(PascalVisitor visitor);
}