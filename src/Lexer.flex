import java.io.*;

%%
%public
%class Lexer
%type String
%line
%column

LETRA = [a-zA-Z]
DIGITO = [0-9]
IDENTIFICADOR = {LETRA}({LETRA}|{DIGITO}|"_")*
NUM_ENTERO = {DIGITO}+
NUM_DECIMAL = {DIGITO}+"."{DIGITO}+
ESPACIOS = [ \t\r\n]+

%%

"//"([^\n])*              { /* Ignorar comentarios */ }

"entero"|"decimal"|"si"|"sino"|"mientras"|"imprimir" { return "RESERVADA|" + yytext(); }

{NUM_DECIMAL}             { return "NUM_DECIMAL|" + yytext(); }
{NUM_ENTERO}              { return "NUM_ENTERO|" + yytext(); }

"+"|"-"|"*"|"/"           { return "OP_MATEMATICO|" + yytext(); }
"="|">"|"<"|"=="          { return "OP_RELACIONAL|" + yytext(); }
"("|")"|"{"|"}"           { return "SIMBOLO|" + yytext(); }
";"                       { return "FIN_SENTENCIA|" + yytext(); }

{IDENTIFICADOR}           { return "IDENTIFICADOR|" + yytext(); }

{ESPACIOS}                { /* Ignorar espacios */ }

.                         { return "ERROR|" + yytext(); }