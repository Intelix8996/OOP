@echo off

rem This script builds library to ./build_manual directory
rem ./bin - compiled Java classes
rem ./doc - generated javadocs
rem ./jar - library after packing to .jar

rem Generate javadocs
javadoc -d .\build_manual\doc\ -charset utf-8 -sourcepath .\src\main\java\ -subpackages ru.nsu.nrepin

rem Compile .java files
javac -sourcepath .\src\main\java\ -d .\build_manual\bin\ .\src\main\java\ru\nsu\nrepin\HeapSort.java

rem Generate .jar archive
mkdir .\build_manual\jar\
jar cf .\build_manual\jar\HeapSort.jar -C .\build_manual\bin\ .

rem Ready-to-Use library HeapSort.jar