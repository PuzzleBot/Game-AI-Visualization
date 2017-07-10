CLASSPATH = -classpath "./Model:./View:./Controller"
JAVAFILES = ./Model/*.java ./View/*.java

all:
	javac $(JAVAFILES)

run:
	java $(CLASSPATH) AIvis