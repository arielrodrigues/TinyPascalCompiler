package semanticAnalyzer.Binding;

import abstractSyntax.FormalParameter.RefOrValue;
import abstractSyntax.labelsAndTypes.TypeDenoter;

public class NormalParameter extends Parameter {
    public TypeDenoter type;

    public NormalParameter(RefOrValue mechanism, TypeDenoter type) {
        super(mechanism);
        this.type = type;
    }
}
