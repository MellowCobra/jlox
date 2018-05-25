Terminals

*   LEFT_PAREN `(`
*   RIGHT_PAREN `(`
*   LEFT_BRACE `{`
*   RIGHT_BRACE `}`
*   COMMA `,`
*   DOT `.`
*   MINUS `-`
*   PLUS `+`
*   SEMICOLON `;`
*   SLASH `/`
*   STAR `*`
*   BANG `!`
*   BANG_EQUAL `!=`
*   EQUAL `=`
*   GREATER `>`
*   GREATER_EQUAL `>=`
*   LESS `<`
*   LESS_EQUAL `<=`
*   IDENTIFIER `_someIdentifier1`
*   STRING `"A String Literal"`
*   NUMBER `1234.5`
*   AND `and`
*   CLASS `class`
*   ELSE `else`
*   FALSE `false`
*   FUN `fun`
*   FOR `for`
*   IF `if`
*   NIL `nil`
*   OR `or`
*   PRINT `print`
*   RETURN `return`
*   SUPER `super`
*   THIS `this`
*   TRUE `true`
*   VAR `var`
*   WHILE `while`
*   EOF `\0`

Grammar:

    program             → declaration* EOF
                        ;

    declaration         → fun_declaration
                        | var_declaration
                        | statement
                        ;

    fun_declaration     → FUN function
                        ;

    function            → IDENTIFIER LEFT_PAREN parameters? RIGHT_PAREN block
                        ;

    parameters          → IDENTIFIER ( COMMA IDENTIFIER )*
                        ;

    var_declaration     → VAR IDENTIFIER ( EQUAL expression )? SEMICOLON
                        ;

    statement           → expr_statement
                        | for_statement
                        | if_statement
                        | print_statement
                        | return_statement
                        | while_statement
                        | block
                        ;

    for_statement       → FOR LEFT_PAREN ( var_decl | expr_statement ) SEMICOLON expression? SEMICOLON expression? RIGHT_PAREN statement
                        ;

    if_statement        → IF LEFT_PAREN expression RIGHT_PAREN statement ( ELSE statement )?
                        ;

    block               → RIGHT_BRACE declaration* LET_BRACE
                        ;

    expr_statement      → expression SEMICOLON
                        ;

    print_statement     → PRINT expression SEMICOLON
                        ;

    while_statement     → WHILE LEFT_PAREN expression RIGHT_PAREN statement
                        ;

    return_statement    → RETURN expression? SEMICOLON
                        ;

    expression          → assignment
                        ;

    assignment          → identifier EQUAL assignment
                        | logic_or
                        ;

    logic_or            → logic_and ( OR logic_and )*
                        ;

    logic_and           → equality ( AND equality )*
                        ;

    equality            → comparison ( ( BANG_EQUAL | EQUAL_EQUAL ) comparison )*
                        ;

    comparison          → addition ( ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) addition )*
                        ;

    addition            → multiplication ( ( MINUS | PLUS )  multiplication )*
                        ;

    multiplication      → unary ( ( SLASH | STAR ) unary )*
                        ;

    unary               → ( BANG | MINUS ) unary
                        | call
                        ;

    call                → primary ( LEFT_PAREN arguments? RIGHT_PAREN )*
                        ;

    arguments           → expression ( COMMA expression )*
                        ;

    primary             → NUMBER
                        | STRING
                        | FALSE
                        | TRUE
                        | NIL
                        | LEFT_PAREN expression RIGHT_PAREN
                        | IDENTIFIER
                        | lambda
                        ;

    lambda              → FUN LEFT_PAREN parameters? RIGHT_PAREN body
                        ;f
