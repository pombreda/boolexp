boolexp
=======

Um simples avaliador de satisfatibilidade para expressões booleanas.

Para compilar:
> make

Para executar:
> java -jar SAT.jar

Aceita os conectores lógicos:
NOT     - "!"
AND     - "&"
IMPLIES - "->"

Exemplo:
```
java -jar SAT.jar 
Entre com a expressão booleana: 
a & b

Tabela verdade:
a|b

F|F	F
F|T	F
T|F	F
T|T	T

Satisfeita
```