Build a json parser per requirements: https://codingchallenges.substack.com/p/coding-challenge-2

How to create a linux command:
1. After completing the code, create a jar file (create a fat jar using plugins given in the pom.xml
2. Use picocli library to create the command as you can easily add various arg param (required and optional), additionally it adds default ones such as -h, -v etc.
3. create a shell script and use the jar files and pass in the args "$@"
4. Add this shell script to a path that is part of the PATH variable.
5. Now you are good to run the command:
    a. json -f /path/to/your/json/file