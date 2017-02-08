package Aulax;

public class PlusExp extends Exp {
    private Exp left, right;

    PlusExp (Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        return (left.evaluate() + right.evaluate());
    }

    public String toString() {
        return (!(left instanceof NumExp)?'(' + left.toString() + ')':left.toString()) + " * " +
                (!(right instanceof NumExp)?'(' + right.toString() + ')':right.toString());
    }

    public Exp getExpLeft() {
        return this.left;
    }

    public Exp getExpRight() {
        return this.right;
    }

}
