package abstractSyntax.Stm;

import visitor.PascalVisitor;

public class EmptyStatement extends Statement {
    @Override
    public void accept(PascalVisitor visitor) {
        // nothing to do here
    }
}