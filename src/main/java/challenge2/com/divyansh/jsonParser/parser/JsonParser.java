package challenge2.com.divyansh.jsonParser.parser;

import challenge2.com.divyansh.jsonParser.entity.*;
import challenge2.com.divyansh.jsonParser.exception.DuplicateKeyException;
import challenge2.com.divyansh.jsonParser.exception.InvalidJsonException;
import challenge2.com.divyansh.jsonParser.exception.ParsingException;
import challenge2.com.divyansh.jsonParser.lexer.Lexer;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "json", mixinStandardHelpOptions = true, description = "parses a given json file")
public class JsonParser implements Runnable {

    @CommandLine.Option(names = {"-f", "--file"}, description = "File path", required = true)
    private String filePath;

    private Lexer lexer;
    private Token nextToken;
    public JsonParser() {
    }

    public JsonParser(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            this.lexer = new Lexer(new File(filePath));
            var node = this.parse();
            System.out.println("Here is the JSON Data:");
            System.out.println(node.serialize(new JsonSerializer()));
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (InvalidJsonException e) {
            e.printStackTrace();
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    public JsonNode parse() throws ParsingException, InvalidJsonException, DuplicateKeyException {
        JsonNode node = parseObject();
        setNextToken();
        assertTokenType(nextToken, TokenType.EOF);
        return node;
    }

    private JsonNode parseObject() throws ParsingException, InvalidJsonException, DuplicateKeyException {
        setNextTokenIfNull();
        assertTokenType(nextToken, TokenType.OPEN_OBJECT);
        JsonObject object = new JsonObject();
        while(true) {
            setNextToken();
            if(nextToken.getType() == TokenType.CLOSE_OBJECT && object.isEmpty())
                break;
            assertTokenType(nextToken, TokenType.STRING);

            JsonMember member = parseJsonMember(nextToken);
            object.addMember(member.getKey(), member);

            setNextToken();
            if(nextToken.getType() == TokenType.CLOSE_OBJECT)
                break;
            assertTokenType(nextToken, TokenType.COMMA);
        }
        assertTokenType(nextToken, TokenType.CLOSE_OBJECT);
        return object;
    }

    private JsonNode parseArray() throws ParsingException, InvalidJsonException, DuplicateKeyException {
        setNextTokenIfNull();
        assertTokenType(nextToken, TokenType.OPEN_ARRAY);
        JsonArray array = new JsonArray();
        while (true) {
            setNextToken();
            if(nextToken.getType() == TokenType.CLOSE_ARRAY)
                break;
            var node = parseValue(nextToken);
            array.addNode(node);
            setNextToken();
            if(nextToken.getType() == TokenType.CLOSE_ARRAY)
            break;
            assertTokenType(nextToken, TokenType.COMMA);
        }
        assertTokenType(nextToken, TokenType.CLOSE_ARRAY);
        return array;
    }

    private JsonMember parseJsonMember(Token token) throws ParsingException, InvalidJsonException, DuplicateKeyException {
        assertTokenType(token, TokenType.STRING);
        setNextToken();
        assertTokenType(nextToken, TokenType.COLON);
        setNextToken();
        return new JsonMember(token.string(), parseValue(nextToken));
    }

    private JsonNode parseValue(Token token) throws ParsingException, InvalidJsonException, DuplicateKeyException {
        switch (token.getType()) {
            case BOOLEAN:
                return new JsonBoolean((Boolean) nextToken.getValue());
            case CHARACTER:
                return new JsonCharacter((Character) nextToken.getValue());
            case NUMBER:
                return new JsonNumber((Number) nextToken.getValue());
            case NULL:
                return new JsonNull();
            case STRING:
                return new JsonString((String) nextToken.getValue());
            case OPEN_OBJECT:
                return parseObject();
            case OPEN_ARRAY:
                return parseArray();
            default:
                throw new InvalidJsonException(String.format(ErrorMessages.UNEXPECTED_TOKEN, token.string()));
        }
    }

    private Token setNextToken() throws ParsingException {
        try {
            this.nextToken = lexer.getNextToken();
            return this.nextToken;
        } catch (ParsingException e) {
            throw e;
        }
    }

    private void setNextTokenIfNull() throws ParsingException {
        if(nextToken == null) {
            setNextToken();
        }
    }

    private void assertTokenType(Token token, TokenType expectedType) throws InvalidJsonException {
        if(token.getType() != expectedType) {
            throw new InvalidJsonException(String.format(ErrorMessages.UNEXPECTED_TOKEN, token.string()));
        }
    }

    public static void main(String... args) {
//        System.out.println(args[0]);
        CommandLine commandLine = new CommandLine(
                new JsonParser());
        commandLine.execute(args);

//        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step5/valid6.json";
//        var parser = new JsonParser(filePath);
//        try {
//            var node = parser.parse();
//            System.out.println(node.serialize(new JsonSerializer()));
//        } catch (ParsingException e) {
//            e.printStackTrace();
//        } catch (InvalidJsonException e) {
//            e.printStackTrace();
//        } catch (DuplicateKeyException e) {
//            e.printStackTrace();
//        }
    }

//
//    java -jar ./target/john-crickett-challenges-1.0-SNAPSHOT-jar-with-dependencies.jar test
//     TODO: is giving null pointer exception. fix it. Use picocli CommandLine.
}
