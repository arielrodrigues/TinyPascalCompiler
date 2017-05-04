program entradaCompleta;

{ Declaração de Labels }
label 12, 27, 34;

{ Declaração de Tipos }
type
	mesesVerao = (dezembro, janeiro, feveireiro, marco, abril);

{ Declaração de Variáveis }
var
	palavra: array [1..5] of char; {Array}
	matriz: array[0..9, mesesVerao, 'a'..'z'] of integer; {Array + IdType}
	dias: (domingo, segunda, terca, quarta, quinta, sexta, sabado); {EnumeratedType}
	num1, num2, num3: integer; {PrimitiveType - Integer}
	numMenor, numMaior: integer; {PrimitiveType - Integer}
	letra: char; {PrimitiveType - Char}
	frase: string; {PrimitiveType - String}
	isPrimo: boolean; {PrimitiveType - Boolean}
	intervalo: -5..5; {SubrangeType}

{ Declaração de Funções e Procedimentos }
procedure findMin; {Procedimento sem Parâmetro}
	{ Função aninhada }
	function f(a:integer):integer;
		var local:integer;
			s:string;
	begin
		s := 'sdfa'+ s + 1; { Assignment Incorreto }
		local := f; { Assignment Incorreto }
	end;
begin
	{ IfStatement Completo }
	if num1 < num2 then
		numMenor := num1
	else
		numMenor := num2;

	{ IfStatement Curto }
	if num3 < numMenor then
		numMenor := num3;
end;

function findMax(a: array[menor..maior: integer] of integer): integer; {Função com Parâmetro Conformante 1D}
begin
	findMax := maior;
end;

function max(i: integer; j: integer): integer; {Função com Parâmetro Normal}
begin
	{ IfStatement Completo }
	if i > j then
		max := i
	else
		max := j + max(i, j);
end;

{ Corpo do Programa }
begin
	{ Assignments }
	palavra[1] := 'a';
	{palavra[1] := 77; {Assigment incorreto}

	matriz[0, dezembro, 'b'] := 10;
	matriz[0][janeiro]['a'] := 7;
	{matriz[0, 0, 0] := 5; {Assigment incorreto}

	dias := quarta;
	{dias := dezembro; {Assigment incorreto}

	num1 := 1;
	{num1 := 'hello'; {Assigment incorreto}

	num2 := 2;
	{num2 := false; {Assigment incorreto}

	num3 := 3;
	{num3 := '3'; {Assigment incorreto}

	letra := 'n';
	{letra := 'hey'; {Assigment incorreto}

	frase := 'hello world!';
	{frase := 8; {Assigment incorreto}

	intervalo := 4;
	{intervalo := 10; {Assignment incorreto}


	{ LabeledStatement + ProcedureStatement }
	12: findMin;
	{ GotoStatement }
	goto 12;
	{goto 14; { Goto Inválido }

	{ WhileStatement }
	while num1 < num3 do
	begin
		if num3 mod num1 = 0 then
			isPrimo := false;

		num1 := num1+1;
	end;

	{ Expressões }
	num1 := 1 + 2;
	num1 := 4 * 5 + 3 * 4 + 2;
	num1 := -7 + 10;
	num1 := 10 + -7;
	isPrimo := true and false;
	isPrimo := true or false;

end.
