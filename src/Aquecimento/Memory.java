package Aula03;

import java.util.HashMap;

public class Memory {
    private static HashMap<String, Integer> memory = new HashMap<>();

    public static Integer get(String var) throws Exception {
        if (memory.get(var) != null)
            return memory.get(var);
        else
            throw new Exception("...");
    }

    public static void put(String var, int val) {
        memory.put(var, val);
    }

}
