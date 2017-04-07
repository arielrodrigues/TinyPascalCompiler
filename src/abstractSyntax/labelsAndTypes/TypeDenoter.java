package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public abstract class TypeDenoter{
    public abstract Object accept(PascalVisitor visitor);
}