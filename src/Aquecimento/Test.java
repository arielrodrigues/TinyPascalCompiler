package Aquecimento;

import Aquecimento.Exp.*;
import Aquecimento.Operations.*;

public class Test {

    public static void main(String[] args) throws Exception {
        Exp exp = new TimesExp(new PlusExp(new NumExp(87), new VarExp("a")),
                new PlusExp(new NumExp(5), new NumExp(8)));
        Memory.put("a", 5);
        System.out.println(exp.toString() + " = " + exp.evaluate());

    }

}
