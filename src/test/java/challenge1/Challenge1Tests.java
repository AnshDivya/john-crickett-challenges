package challenge1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Challenge1Tests {
/*
* 1. FIX: isPipedInput does not seem to be working properly when we run following command.
* 2. Add tests
* */
    @Test
    public void testOption_a() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-a", "test.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s = in.readLine();
            Assertions.assertEquals("342190 339292 7145 58164 test.txt", s);
        }
    }

    @Test
    public void testOption_c() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-c", "test.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s = in.readLine();
            Assertions.assertEquals("342190 test.txt", s);
        }
    }

    @Test
    public void testOption_w() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-w", "test.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s = in.readLine();
            Assertions.assertEquals("58164 test.txt", s);
        }
    }

    @Test
    public void testOption_l() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-l", "test.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s = in.readLine();
            Assertions.assertEquals("7145 test.txt", s);
        }
    }

    @Test
    public void testOption_m() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-m", "test.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s = in.readLine();
            Assertions.assertEquals("339292 test.txt", s);
        }
    }

    @Test
    public void testOption_invalidOption() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-in", "test.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s = err.readLine();

            Assertions.assertEquals("Invalid option: -in", s);
        }
    }

    @Test
    public void testOption_invalidFile() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("Can't test ccwc command on windows");
        } else {
            ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/java/Ccwc.jar", "-a", "test2.txt");
            builder.directory(new File("/home/anshdivya/IdeaProjects/john-crickett-challenges/src/main/resources"));
            Process p = builder.start();
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s = err.readLine();

            Assertions.assertEquals("File not found: test2.txt", s);
        }
    }
}
