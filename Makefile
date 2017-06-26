all:
	javac Server.java Client.java -Xlint
server:
	javac Server.java -Xlint
client:
	javac Client.java -Xlint
clean:
	find -name '*.class' -delete
	find -name '.*.un~' -delete
run:
	java Client
