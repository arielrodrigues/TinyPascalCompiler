package semanticAnalyzer.Binding;

import abstractSyntax.labelsAndTypes.TypeDenoter;

public class NormalParameter extends Parameter {
    TypeDenoter type;

    public NormalParameter(TypeDenoter type) {
        super();
        this.type = type;
    }
}
