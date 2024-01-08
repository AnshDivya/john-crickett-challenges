1. Compile the file using javac command
2. To make a jar file:
    jar cfe <file-name> <entry-point-package.entry-point-className.java> <list-of-all-packages>

3. To check if the input is from console or piped from some other command: isPipedInput simply checks whether the input is from a console or not.

4. Finally, to run the program via the ccwc command:
   1. make a shell script with the same name.
    2. make it executable (via chmod +x command).
    3. add its path to the end of ~/.bashrc file. For example:
       1. export PATH=$PATH:$HOME/xx/..../xx/src/main/java/challenge1
    
5. Now run the command:
    1. ccwc -r challenge1/Ccwc.java 
    2. cat ../resources/test.txt | ccwc -m

