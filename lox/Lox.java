package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    private static final Interpreter interpreter = new Interpreter();
    static Boolean hadError = false;
    static Boolean hadRuntimeError = false;

    public static void main(String args[]) {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
        } else if (args.length == 1) {
            try {
                runFile(args[0]);
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        } else {
            try {
                runPrompt();
            } catch (IOException e) {
                System.err.println(e.toString());
            }
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) {
            System.exit(65);
        }
        if (hadRuntimeError) {
            System.exit(70);
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            run(reader.readLine());

            hadError = false;
        }
    }

    private static void run(String source) {

        // Lexical Analysis
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // Parsing
        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        // Stop if syntax error
        if (hadError) {
            return;
        }

        // Scope Resolution
        Resolver resolver = new Resolver(interpreter);
        resolver.resolve(statements);

        if (hadError) {
            return;
        }

        // Code Interpretation
        interpreter.interpret(statements);
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
    }
}