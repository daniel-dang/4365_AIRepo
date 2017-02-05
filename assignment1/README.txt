========== GROUP MEMBERS ===========
Daniel Dang and Mavis Francia

======= LIST OF SOURCE FILES =======
	AStar.java
	BFS.java
	Comparators.java
	DFS.java
	Greedy.java
	Main.java
	Node.java
	SearchTree.java
	UCS.java

============= PLATFORM =============
Developed on both Windows and Mac OS X using the IntelliJ IDE

========== KNOWN PROBLEMS ==========
[none]

======== HOW TO RUN PROGRAM ========

1. Open a terminal and navigate to the directory containing the source code.
2. Ensure that input files are in the same directory as the .java source code files.
2. Execute the following command to compile all the java files:
		javac *.java
3. Execute the following command to run the program:
		java Main [-cost] <BFS|DFS|UCS|GS|A-star> <inputfile>
   ----------------------------------------
   Example usages:
   		java Main BFS input1.txt
   		java Main UCS input2.txt
        java Main -cost A-star input3.txt
   ----------------------------------------