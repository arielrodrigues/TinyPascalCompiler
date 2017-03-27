package abstractSyntax.Stm;

import visitor.PasVisitor;

public class EmptyStatement extends Statement {
    @Override
    public void accept(PasVisitor visitor) {
        // nothing to do here
    }
}