package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public interface TypeIdOrPrimitive {
//TODO ***** IdType e Primitive devem implementar esta interface

    Object accept(PascalVisitor visitor);
}