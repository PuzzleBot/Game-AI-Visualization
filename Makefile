CLASSPATH = -classpath "./Model:./View:./Controller"
JAVAFILES = ./Model/*.java ./View/*.java ./Controller/*.java

TEST_CLASSPATH = -classpath "./Model:./View:./Controller:./Tests:./junit/junit-4.12.jar:./junit/hamcrest-core-1.3.jar"
TEST_JAVAFILES = ./Model/*.java ./View/*.java ./Controller/*.java ./Tests/*.java
TEST_CORE = org.junit.runner.JUnitCore

all:
	javac $(JAVAFILES)

run:
	java $(CLASSPATH) AIvis

test: testComp testRun

testComp:
	javac $(TEST_CLASSPATH) $(TEST_JAVAFILES)

testRun: testVector2D testEntitySpace

testVector2D:
	java $(TEST_CLASSPATH) $(TEST_CORE) Vector2DTest

testEntitySpace:
	java $(TEST_CLASSPATH) $(TEST_CORE) EntitySpaceTest
	