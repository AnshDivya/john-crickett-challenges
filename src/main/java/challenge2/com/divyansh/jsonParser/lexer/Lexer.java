package challenge2.com.divyansh.jsonParser.lexer;

import challenge2.com.divyansh.jsonParser.entity.Constants;
import challenge2.com.divyansh.jsonParser.entity.ErrorMessages;
import challenge2.com.divyansh.jsonParser.entity.Token;
import challenge2.com.divyansh.jsonParser.entity.TokenType;
import challenge2.com.divyansh.jsonParser.exception.ParsingException;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*   TODO: Complete Lexer: it should be able to identify all the tokens correctly
*    1. Identify numbers:
*       a. Positive and negative numbers
*       b. Fractions
*       c. Numbers in exponent format
*       d. invalid numbers
*    2. Boolean values
*       a. true
*       b. false
*    3. null
*    4. Array values
*    5. Object values
*    6. Double Quotes string
*    7. Single quotes character
*    8. Comma
*    9. Should be able to ignore whitespaces, tabs and newlines
*    10. Throw exception if the token is none of the above
 */
public class Lexer {

    private Reader reader;
    private Queue<Character> queue;
    public Lexer(File file) {
        try {
            this.reader = new FileReader(file);
            this.queue = new LinkedList<>();
            this.setupQueue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupQueue() throws IOException {
        var ch = this.reader.read();
        while(ch != -1) {
            this.queue.add((char) ch);
            ch = this.reader.read();
        }
    }

    public Token getNextToken() throws ParsingException {
        if(this.queue.isEmpty()) {
            return new Token(TokenType.EOF, Constants.EOF);
        }
        var ch = this.queue.peek();
        switch (ch) {
            case 't':
                var str = readString();
                if(!str.equals(Constants.TRUE)) {
                    throw new ParsingException(String.format(ErrorMessages.INVALID_LITERAL_FORMAT, str));
                }
                return new Token(TokenType.BOOLEAN, Boolean.valueOf(str));

            case 'f':
                str = readString();
                if(!str.equals(Constants.FALSE)) {
                    throw new ParsingException(String.format(ErrorMessages.INVALID_LITERAL_FORMAT, str));
                }
                return new Token(TokenType.BOOLEAN, Boolean.valueOf(str));

            case ' ':
            case '\t':
            case '\n':
                this.queue.poll();
                return getNextToken();

            case 'n':
                str = readString();
                if(!str.equals(Constants.NULL)) {
                    throw new ParsingException(String.format(ErrorMessages.INVALID_LITERAL_FORMAT, str));
                }
                return new Token(TokenType.NULL, str);

            case Constants.COMMA_SEPARATOR:
                this.queue.poll();
                return new Token(TokenType.COMMA, Constants.COMMA_SEPARATOR);

            case Constants.COLON:
                this.queue.poll();
                return new Token(TokenType.COLON, Constants.COLON);

            case Constants.OPEN_ARRAY:
                this.queue.poll();
                return new Token(TokenType.OPEN_ARRAY, Constants.OPEN_ARRAY);

            case Constants.CLOSE_ARRAY:
                this.queue.poll();
                return new Token(TokenType.CLOSE_ARRAY, Constants.CLOSE_ARRAY);

            case Constants.OPEN_OBJECT:
                this.queue.poll();
                return new Token(TokenType.OPEN_OBJECT, Constants.OPEN_OBJECT);

            case Constants.CLOSE_OBJECT:
                this.queue.poll();
                return new Token(TokenType.CLOSE_OBJECT, Constants.CLOSE_OBJECT);

            case Constants.DOUBLE_QUOTES:
                str = readAfterQuotes(Constants.DOUBLE_QUOTES);
                return new Token(TokenType.STRING, str);

            case Constants.SINGLE_QUOTES:
                str = readAfterQuotes(Constants.SINGLE_QUOTES);
                return new Token(TokenType.CHARACTER, str.charAt(0));
            case '+':
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                str = readString();
                var number = parseNumber(str);
                return new Token(TokenType.NUMBER, number);
            default:
                throw new ParsingException(String.format(ErrorMessages.INVALID_CHARACTER_MESSAGE, ch));
        }
    }

    private String readString() throws ParsingException {
        if(this.queue.isEmpty())
            throw new ParsingException(ErrorMessages.EOF_MESSAGE);
        var sb = new StringBuilder();
        while(this.queue.peek() != Constants.COMMA_SEPARATOR
                && this.queue.peek() != Constants.SPACE
                && this.queue.peek() != Constants.TAB
                && this.queue.peek() != Constants.NEW_LINE
                && this.queue.peek() != Constants.CLOSE_ARRAY
                && this.queue.peek() != Constants.CLOSE_OBJECT
        ) {
            sb.append(this.queue.poll());
            if(this.queue.isEmpty()) {
                throw new ParsingException(ErrorMessages.EOF_MESSAGE);
            }
        }
        return sb.toString();
    }

    private String readAfterQuotes(char quote) throws ParsingException {
        if(this.queue.isEmpty())
            throw new ParsingException(ErrorMessages.EOF_MESSAGE);
        this.queue.poll();
        var sb = new StringBuilder();
        while(this.queue.peek() != quote) {
            sb.append(this.queue.poll());
            if(this.queue.isEmpty()) {
                throw new ParsingException(ErrorMessages.EOF_MESSAGE);
            }
        }
        this.queue.poll();
        if(quote == Constants.SINGLE_QUOTES && sb.length() > 1)
            throw new ParsingException(String.format(ErrorMessages.INVALID_LITERAL_FORMAT, sb));
        return sb.toString();
    }

    private Double parseNumber(String str) throws ParsingException {
        Pattern pattern = Pattern.compile(Constants.NUMBER_REGEX);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()) {
            return Double.parseDouble(str);
        } else {
            throw new ParsingException(String.format(ErrorMessages.INVALID_NUMBER_FORMAT, str));
        }
    }
}
