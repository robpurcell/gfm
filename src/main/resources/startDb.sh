[ -f h2.jar ] && echo "Already downloaded H2 Jar" || curl http://central.maven.org/maven2/com/h2database/h2/1.4.197/h2-1.4.197.jar > h2.jar
java -cp h2*.jar org.h2.tools.Server -baseDir /Users/rob/data -trace
#rm h2.jar
