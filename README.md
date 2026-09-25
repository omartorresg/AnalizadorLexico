# Analizador Léxico con JFlex e Interfaz Gráfica (Swing)

Este proyecto es un Analizador Léxico desarrollado para la materia de Compiladores. Su objetivo es procesar y clasificar los componentes léxicos de un mini-lenguaje de programación, utilizando un Autómata Finito Determinista (AFD) generado con JFlex y una interfaz de usuario creada en Java Swing.


## Características Principales

* **Reconocimiento Automático:** Identifica palabras reservadas, números enteros, decimales, identificadores, operadores y símbolos de agrupación.
* **Manejo de Errores:** Detecta caracteres fuera del alfabeto del lenguaje y los reporta en la tabla de símbolos sin detener la aplicación.
* **Soporte para Comentarios:** Omite automáticamente los comentarios de una sola línea (`//`).
* **Aplicación Gráfica de Escritorio:** Interfaz limpia con tabla interactiva para visualizar los lexemas y sus correspondientes tokens en tiempo real.


## Estructura del Proyecto

```text
AnalizadorLexico/
├── doc/
│   └── Manual.md             # Documentación técnica del mini-lenguaje
├── Ejecutable/
│   └── AnalizadorLexico.jar  # Archivo ejecutable del proyecto
├── lib/
│   └── jflex-full-1.9.1.jar  # Motor generador de JFlex
├── src/
│   ├── InterfazGrafica.java  # Ventana Swing y lógica de la GUI
│   ├── Lexer.flex            # Definición de reglas léxicas y expresiones regulares
│   └── Lexer.java            # Analizador generado automáticamente por JFlex
├── .gitignore                # Reglas de exclusión para Git
└── README.md                 # Presentación general del repositorio
