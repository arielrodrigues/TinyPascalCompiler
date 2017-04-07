package abstractSyntax.labelsAndTypes;

import visitor.PascalVisitor;

public interface TypeIdOrPrimitive {
//TODO ***** TypeId e Primitive devem implementar esta interface

    Object accept(PascalVisitor visitor);
}