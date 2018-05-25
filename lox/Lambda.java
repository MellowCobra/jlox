package com.craftinginterpreters.lox;

import java.util.List;

class Lambda implements LoxCallable {
    private final Expr.Lambda declaration;
    private final Environment closure;

    Lambda(Expr.Lambda declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public int arity() {
        return declaration.parameters.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);

        for (int i = 0; i < declaration.parameters.size(); i++) {
            environment.define(declaration.parameters.get(i).lexeme, arguments.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        return null;
    }

    @Override
    public String toString() {
        String representation = "<lambda (";
        int paramCount = this.declaration.parameters.size();
        for (int i = 0; i < paramCount - 1; i++) {
            Token param = this.declaration.parameters.get(i);
            representation += param.lexeme + ", ";
        }
        representation += this.declaration.parameters.get(paramCount - 1).lexeme + ")>";

        return representation;
    }
}