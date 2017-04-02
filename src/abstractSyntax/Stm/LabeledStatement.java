package abstractSyntax.Stm;

import visitor.PascalVisitor;

public class LabeledStatement extends Statement {
    public int label;
    public Statement stm;

    public LabeledStatement(int label, Statement stm) {
        this.label = label;
        this.stm = stm;
    }

    @Override
    public void accept(PascalVisitor visitor) {
        visitor.VisitLabeledStm(this);
    }
}
