package abstractSyntax.Operator;

import visitor.PascalVisitor;

public enum Sign{
	PLUS, MINUS;
	public void accept (PascalVisitor visitor) {
		visitor.VisitSign(this);
	}
}

/* NÃO ESTAMOS UTILIZANDO, NOSSO SINAL É ENVIADO DIRETO DO ANALISADOR LÉXICO */