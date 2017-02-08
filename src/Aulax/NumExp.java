package Aulax;

public class NumExp extends Exp{
    private int number;

    NumExp(int number) {
        this.number = number;
    }

    public int evaluate() {
        return this.number;
    }

    public String toString() {
        return Integer.toString(this.number);
    }

    public int getNumber() {
        return this.number;
    }
}
