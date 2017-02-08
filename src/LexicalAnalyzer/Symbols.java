package LexicalAnalyzer;

import java.util.HashMap;

public class Symbols {
    HashMap<String, Long> tokens;

    public void printTokens() {
        System.out.println("*****************************************");
        System.out.println("Tokens:");
        tokens.forEach((token, num) -> {
            System.out.print(token + ": " + num + ", ");
        });
        System.out.println("*****************************************");
    }
}
