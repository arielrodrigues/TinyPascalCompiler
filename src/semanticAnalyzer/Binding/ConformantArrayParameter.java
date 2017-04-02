package semanticAnalyzer.Binding;

import abstractSyntax.ConformantArray.ConformantArraySchema;

public class ConformantArrayParameter extends Parameter {
    ConformantArraySchema schema;

    public ConformantArrayParameter (ConformantArraySchema schema) {
        super();
        this.schema = schema;
    }
}
