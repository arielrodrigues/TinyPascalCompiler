package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public abstract class TypeDenoter{
    public abstract void accept(PascalVisitor visitor);
}