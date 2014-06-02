all: SAT.jar

SAT.jar: bin/SAT.class
	jar cfm SAT.jar MANIFEST.MF bin/*

bin/SAT.class: \
    src/BooleanOperators.java \
    src/IdentifierNode.java \
	src/BoolExpTree.java \
	src/CompoundNode.java \
	src/Util.java \
	src/ConstantNode.java \
	src/ExpNode.java \
	src/SAT.java
	javac src/*.java -d bin/

clean:
	rm -rf *.jar bin/*.class
