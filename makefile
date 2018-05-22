J = java
JC = javac
JFLAGS = -g
.SUFFIXES = .java .class
.java.class:
	$(JC) */*.java -d dist

default: .java.class

build-lox:
	$(JC) lox/*.java -d dist

build-tool:
	$(JC) tool/*.java -d dist

clean:
	find . -name '*.class' -delete
	$(RM) -R dist/*

run-repl:
	$(J) -classpath dist com/craftinginterpreters/lox/Lox

run-jlox:
	$(J) -classpath dist com/craftinginterpreters/lox/Lox $(file)

run-gen-ast:
	$(J) -classpath dist com/craftinginterpreters/tool/GenerateAst lox