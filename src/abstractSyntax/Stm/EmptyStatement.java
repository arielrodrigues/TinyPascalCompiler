package abstractSyntax.Stm;

import visitor.PascalVisitor;

public class EmptyStatement extends Statement {
    @Override
    public Object accept(PascalVisitor visitor) {
        return visitor.VisitEmptyStm(this);
    }
}