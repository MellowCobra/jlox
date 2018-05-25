# jlox

> Interpreter for the lox programming language, written in Java.

## Grammar

The Context Free Grammar for lox in this interpreter can be found in [CFG.md](https://github.com/MellowCobra/jlox/blob/master/CFG.md)

# Build and Run

Must have java and the `jdk` installed, as well as `cmake`
I am working on a pre-built branch which will house the built code in the `dist` directory.

Build with

*   `make`

Run REPL with

*   `make run-repl`

Or interpret a source file with

*   `make run-jlox file={filename.lox}`

# Language Constructs

*   `for` - For Loop Statement
*   `while` - While Loop Statement
*   `print` - Print Statement
*   `var` - Variable Declaration Statement
*   `fun` - Function Declaration Statement
*   `fun` - Lambda Declaration Expression
*   `return` - Return Statement
*   more!

# Native Library

The Native Library for Lox is currently limited to 3 functions:

*   clock
*   print
*   stdin

### clock

`clock () returns Number`

Returns the difference between the current time and midnight, January 1, 1970 UTC, measured in seconds.
Useful for benchmarking.

```
var begin = clock();

var a = 0;
var b = 1;
for (var i = 0; i < 100000; i++) {
    // Calculate fibonacci numbers
    var temp = a;
    a = b;
    b = a + temp;
    print b;
}

var end = clock();

print end - begin; // The time it took to run in seconds
```

### print

`print ( value ) returns nil`

Prints the input value to stdout.

```
print("The result of 2 + 2 is " + 2 + 2);
```

`print` is also a built in statement. It can be invoked like so: `print [expression];`

```
print "The result of 2 + 2 is " + 2 + 2;
```

### stdin

`stdin () returns String`

Returns a line read from stdin.

```
print "What is your name?";
var name = stdin();
print "Hello, " + name + "!";
```
