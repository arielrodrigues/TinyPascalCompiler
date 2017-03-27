package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

public abstract class TypeIdOrOrdinal extends TypeDenoter {
    public abstract void accept(PasVisitor visitor);
}