package Aquecimento.Stm;

import Aquecimento.Exp.Exp;
import Aquecimento.Memory;

public class AssignStm extends Stm {
    public String id;
    public Exp exp;
    public AssignStm(String i, Exp e) {id = i; exp = e;}
    public void evaluate() throws Exception {
        Memory.put(id, exp.evaluate());
    }
    public String toString() {
        return id + " = " + exp.toString();
    }
}
