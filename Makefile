CLASSPATH = -classpath "./Model:./View:./Controller"
JAVAFILES = ./Model/*.java ./View/*.java ./Controller/*.java

all:
	javac $(JAVAFILES)

run:
	java $(CLASSPATH) AIvis