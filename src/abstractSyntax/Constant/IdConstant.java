package abstractSyntax.Constant;

import visitor.PascalVisitor;

public class IdConstant extends Constant {
    public String id;

    public IdConstant(String id) {
        super();
        this.id = id;
    }

    @Override
    public Object accept(PascalVisitor visitor) {
        return visitor.VisitConstantId(this);
    }
}
