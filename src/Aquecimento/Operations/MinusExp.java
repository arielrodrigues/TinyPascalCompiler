package Aula03.Operations;

import Aula03.Exp.Exp;
import Aula03.Exp.VarExp;

public class MinusExp {
    private Exp left, right;

    public MinusExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate() throws Exception {
        return (left.evaluate() - right.evaluate());
    }

    public String toString() {
        return (!(left instanceof NumExp || left instanceof VarExp)?'(' + left.toString() + ')':left.toString()) + " - " +
                (!(right instanceof NumExp || left instanceof VarExp)?'(' + right.toString() + ')':right.toString());
    }

    public Exp getExpLeft() {
        return this.left;
    }

    public Exp getExpRight() {
        return this.right;
    }
}
