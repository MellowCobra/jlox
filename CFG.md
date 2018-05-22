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

    program         → declaration* EOF
                    ;

    declaration     → var_declaration
                    | statement
                    ;

    var_declaration → VAR IDENTIFIER ( EQUAL expression )? SEMICOLON
                    ;

    statement       → expr_statement
                    | print_statement
                    | block
                    ;

    block           → RIGHT_BRACE declaration* LET_BRACE
                    ;

    expr_statement  → expression SEMICOLON
                    ;

    print_statement → PRINT expression SEMICOLON
                    ;

    expression      → assignment
                    ;

    assignment      → identifier EQUAL assignment
                    | equality
                    ;

    equality        → comparison ( ( BANG_EQUAL | EQUAL_EQUAL ) comparison )*
                    ;

    comparison      → addition ( ( GREATER | GREATER_EQUAL | LESS | LESS_EQUAL ) addition )*
                    ;

    addition        → multiplication ( ( MINUS | PLUS )  multiplication )*
                    ;

    multiplication  → unary ( ( SLASH | STAR ) unary )*
                    ;

    unary           → ( BANG | MINUS ) unary
                    | primary
                    ;

    primary         → NUMBER
                    | STRING
                    | FALSE
                    | TRUE
                    | NIL
                    | LEFT_PAREN expression RIGHT_PAREN
                    | IDENTIFIER
                    ;
