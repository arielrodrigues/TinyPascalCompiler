package Aulax;

public class PlusExp extends Exp {
    private Exp left, right;

    PlusExp (Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return left.toString() + " + " + right.toString();
    }

    public Exp getExpLeft() {
        return this.left;
    }

    public Exp getExpRight() {
        return this.right;
    }

}
