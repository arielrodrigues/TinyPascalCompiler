package Aulax;

public class TimesExp extends Exp {
    private Exp left, right;

    public TimesExp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }

    public String toString() {
        return (!(left instanceof NumExp)?'(' + left.toString() + ')':left.toString()) + " * " +
                (!(right instanceof NumExp)?'(' + right.toString() + ')':right.toString());
    }

    public Exp getLeft() {
        return this.left;
    }

    public Exp getRight() {
        return this.right;
    }

}
