package Aquecimento.Operations;

import Aquecimento.Exp.Exp;
import Aquecimento.Exp.VarExp;

public class TimesExp extends Exp {
    private Exp left, right;

    public TimesExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate() throws Exception {
        return left.evaluate() * right.evaluate();
    }

    public String toString() {
        return (!(left instanceof NumExp || left instanceof VarExp)?'(' + left.toString() + ')':left.toString()) + " * " +
                (!(right instanceof NumExp || left instanceof VarExp)?'(' + right.toString() + ')':right.toString());
    }

    public Exp getLeft() {
        return this.left;
    }

    public Exp getRight() {
        return this.right;
    }

}
