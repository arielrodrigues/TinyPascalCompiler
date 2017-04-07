package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public abstract class TypeIdOrOrdinal extends TypeDenoter {
    public abstract Object accept(PascalVisitor visitor);
}