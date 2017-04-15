package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public interface TypeIdOrPrimitive {
    public Object accept(PascalVisitor visitor);
}
