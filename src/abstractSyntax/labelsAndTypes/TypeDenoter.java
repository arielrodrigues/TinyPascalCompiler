package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

public abstract class TypeDenoter{
    public abstract void accept(PasVisitor visitor);
}