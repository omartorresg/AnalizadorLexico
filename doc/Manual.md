# Manual de Especificación Técnica: Lenguaje MiniCode

# 1. Introducción
MiniCode es un lenguaje de programación imperativo y fuertemente tipado en su diseño conceptual, desarrollado con fines didácticos para la asignatura de Compiladores. Este manual documenta la estructura léxica del lenguaje, detallando su alfabeto, las palabras reservadas, las expresiones regulares utilizadas para identificar tokens y las reglas que rigen el manejo de errores léxicos.

El objetivo de este analizador léxico, desarrollado con la herramienta JFlex, es procesar el código fuente de MiniCode carácter por carácter y agruparlos en unidades con significado sintáctico llamadas tokens.

# 2. Alfabeto del Lenguaje
El analizador reconoce los siguientes conjuntos de caracteres válidos:

* Letras: Alfabeto latino en mayúsculas y minúsculas (a-z, A-Z). El lenguaje es sensible a minúsculas en sus palabras reservadas.
* Dígitos: Números arábigos (0-9).
* Símbolos especiales: +, -, *, /, =, >, <, (, ), {, }, ;, ., _
* Caracteres de espacio: Espacios en blanco, tabulaciones, retornos de carro y saltos de línea.

# 3. Clasificación de Tokens

## 3.1. Palabras Reservadas
Son términos con un significado técnico fijo dentro de la sintaxis del lenguaje. No pueden utilizarse como nombres de variables ni identificadores.

Palabra Reservada/Función dentro del Lenguaje

entero: Declaración de variables de tipo numérico entero.
decimal:Declaración de variables numéricas de punto flotante.
si: Estructura condicional (evalúa una condición).
sino: Rama alternativa de la estructura condicional.
mientras: Bucle de repetición condicional.
imprimir: Función estándar para salida de datos.

## 3.2. Identificadores (Variables)
Representan los nombres asignados a variables y posiciones de memoria.

* Patrón de Expresión Regular: [a-zA-Z]([a-zA-Z]|[0-9]|"_")*

Reglas de formación:
1. Todo identificador debe comenzar estrictamente con una letra.
2. Puede contener cualquier combinación de letras, números y guiones bajos (_).
3. Ejemplos válidos: contador, precio_final, var1, limiteDecimal.
4. Ejemplos no válidos: 1variable (comienza con número), precio-total (contiene guión medio).

## 3.3. Literales Numéricas
El lenguaje clasifica los números en dos categorías según su formato:

* Enteros (NUM_ENTERO): Secuencia continua de uno o más dígitos.
  Expresión Regular: [0-9]+
  Ejemplos: 0, 15, 2026.

* Decimales (NUM_DECIMAL): Secuencia de dígitos separada obligatoriamente por un punto.
  Expresión Regular: [0-9]+"."[0-9]+
  Ejemplos: 3.1416, 0.50, 100.0.

## 3.4. Operadores y Delimitadores

* Operadores Matemáticos (OP_MATEMATICO): +, -, *, /
* Operadores Relacionales y Asignación (OP_RELACIONAL):
  = (Asignación)
  == (Comparación de igualdad)
  > (Mayor que)
  < (Menor que)
* Símbolos de Agrupación (SIMBOLO):
  Paréntesis ( y ) para agrupar expresiones.
  Llaves { y } para delimitar bloques de código.
* Terminador de Sentencia (FIN_SENTENCIA): El carácter punto y coma ; delimita el final de cada instrucción.

## 3.5. Comentarios y Espacios en Blanco

* Comentarios de una línea: Comienzan con la secuencia // y se extienden hasta el salto de línea. El analizador los descarta automáticamente.
  Expresión Regular: "//"([^\n])*
* Espacios en blanco: Los espacios, tabulaciones y saltos de línea sirven únicamente como separadores entre tokens y son omitidos durante el escaneo.

# 4. Manejo de Errores Léxicos
Cualquier carácter dentro del archivo fuente que no concuerde con ninguna de las expresiones regulares definidas se clasificará como un ERROR.

* Ejemplos de caracteres no reconocidos: @, $, #, %, &, ~, ?
* Comportamiento del analizador: Al detectar un carácter inválido, el motor no interrumpe la ejecución de la aplicación; simplemente registra la incidencia en la tabla de símbolos indicando el símbolo no reconocido, permitiendo continuar con el análisis del resto del código.

# 5. Ejemplo Completo de Prueba

A continuación se presenta un programa escrito en MiniCode que abarca todos los elementos descritos:

// Ejemplo de prueba para el analizador léxico
entero contador = 0;
decimal limite = 15.5;

si (contador < 10) {
    imprimir contador;
} sino {
    mientras (contador == 10) {
        contador = contador + 1;
    }
}