J = java
JC = javac
JFLAGS = -g
.SUFFIXES = .java .class
.java.class:
	$(JC) */*.java -d dist

default: .java.class

clean:
	$(RM) -r */*.class

run-repl:
	$(J) -classpath dist com/craftinginterpreters/lox/Lox

run-jlox:
	$(J) -classpath dist com/craftinginterpreters/lox/Lox $(file)