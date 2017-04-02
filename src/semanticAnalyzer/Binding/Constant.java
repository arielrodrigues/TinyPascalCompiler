package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class Constant implements Binding {
    TypeDenoter type;

    public Constant(TypeDenoter type) {
        super();
        this.type = type;
    }
}
