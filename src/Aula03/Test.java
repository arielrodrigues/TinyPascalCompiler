package Aula03;

public class Test {

    public static void main(String[] args) {
        Exp exp = new TimesExp(new NumExp(87), new PlusExp(new NumExp(5), new NumExp(8)));
        System.out.println(exp.toString() + " = " + exp.evaluate());
    }

}
