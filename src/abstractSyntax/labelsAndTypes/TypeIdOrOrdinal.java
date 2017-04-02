package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public abstract class TypeIdOrOrdinal extends TypeDenoter {
    public abstract void accept(PascalVisitor visitor);
}