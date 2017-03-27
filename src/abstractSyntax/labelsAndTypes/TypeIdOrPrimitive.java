package abstractSyntax.labelsAndTypes;

import visitor.PasVisitor;

public interface TypeIdOrPrimitive {
//TODO ***** TypeId e Primitive devem implementar esta interface

    void accept(PasVisitor visitor);
}