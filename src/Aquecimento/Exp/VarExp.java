package Aquecimento.Exp;
import Aquecimento.Memory;

public class VarExp extends Exp {
    String var;

    public VarExp(String var) {
        this.var = var;
    }

    public String toString() {
        return var;
    }

    public int evaluate() throws Exception {
        return Memory.get(var);
    }
}
