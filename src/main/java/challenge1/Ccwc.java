package challenge1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

//TBD: Final step

public class Ccwc {
    private static class Result {
        private long byteCount, wordCount, lineCount, charCount;
    }
    public static void main(String... args) {
        if(isPipedInput()) {
            final String option = args.length == 0 ? "-a" : args[0];
            try {
                printResult(option, processCommand(option, System.in), null);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid option: " + option);
            }
        } else {
            if(args.length ==0 || args.length > 2) {
                System.err.println("Usage: ccwc [option(optional)] [filename]");
                return;
            }
            final String option = args.length == 1 ? "-a" : args[0];
            final String filename = args.length == 2 ? args[1] : args[0];
            final File file = new File(filename);

            if(!file.exists() || !file.isFile()) {
                System.err.println("File not found: " + filename);
            }

            try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                printResult(option, processCommand(option, inputStream), file);
            } catch (NoSuchFileException e) {
                System.err.println(String.format("File %s does not exist.", filename));
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid option: " + option);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printResult(String option, Result result, File file) throws IllegalArgumentException {
        switch (option) {
            case "-c":
                System.out.print(result.byteCount + " ");
                break;
            case "-l":
                System.out.print(result.lineCount + " ");
                break;
            case "-w":
                System.out.print(result.wordCount + " ");
                break;
            case "-m":
                System.out.print(result.charCount + " ");
                break;
            case "-a":
                System.out.print(result.byteCount + " " + result.charCount + " " + result.lineCount + " " + result.wordCount + " ");
                break;
            default: {
                throw new IllegalArgumentException();
            }
        }
        if(file != null && file.exists()) {
            System.out.println(file.getName());
        } else {
            System.out.println();
        }
    }

    private static Result processCommand(String option, InputStream inputStream) {
        final var result = new Result();
        try {
            byte[] bytes = inputStream.readAllBytes();
            switch (option) {
                case "-c":
                    result.byteCount = countBytes(bytes);
                    break;
                case "-l":
                    result.lineCount = countLines(bytes);
                    break;
                case "-w":
                    result.wordCount = countWords(bytes);
                    break;
                case "-m":
                    result.charCount = countCharacters(bytes);
                    break;
                case "-a":
                    result.byteCount = countBytes(bytes);
                    result.lineCount = countLines(bytes);
                    result.wordCount = countWords(bytes);
                    result.charCount = countCharacters(bytes);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return result;

    }

    private static boolean isPipedInput() {
        return System.console() == null;
    }

    private static long countCharacters(byte[] bytes) {
        String str = new String(bytes, 0, bytes.length, Charset.forName("UTF-8"));
        return str.length();
    }

    private static int countWords(byte[] bytes) {
        int wordCount = 0;
        int wordLength = 0;
        for(byte b: bytes) {
            if(Character.isWhitespace(b)) {
                if(wordLength > 0) {
                    wordCount++;
                    wordLength = 0;
                }
            } else {
                wordLength++;
            }
        }
        if(wordLength > 0)
            wordCount++;
        return wordCount;
    }

    private static int countLines(byte[] bytes) {
        int lineCount = 0;
        for(int i = 0; i < bytes.length; i++) {
            byte by = bytes[i];
            if(by == '\n') {
                lineCount++;
            }
        }
        return lineCount;
    }

    private static long countBytes(byte[] bytes) {
        return bytes.length;
    }
}
