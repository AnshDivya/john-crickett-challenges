package challenge2;

import challenge2.com.divyansh.jsonParser.entity.*;
import challenge2.com.divyansh.jsonParser.exception.DuplicateKeyException;
import challenge2.com.divyansh.jsonParser.exception.InvalidJsonException;
import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.ParsingException;
import challenge2.com.divyansh.jsonParser.lexer.Lexer;
import challenge2.com.divyansh.jsonParser.parser.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class Step2 {
    @Test
    void test_invalidJson_invalid() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/invalid.json";
        
        var parser = new JsonParser(filePath);

        var exception = Assertions.assertThrows(InvalidJsonException.class, () -> parser.parse());
        Assertions.assertEquals("Unexpected Token: }", exception.getMessage());
    }

    @Test
    void test_invalidJson_invalid2() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/invalid2.json";
        
        var parser = new JsonParser(filePath);

        var exception = Assertions.assertThrows(ParsingException.class, () -> parser.parse());
        Assertions.assertEquals("Encountered an invalid character k while parsing", exception.getMessage());
    }

    @Test
    void test_invalidJson_invalid3() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/invalid3.json";
        
        var parser = new JsonParser(filePath);

        var exception = Assertions.assertThrows(ParsingException.class, () -> parser.parse());
        Assertions.assertEquals("EOF reached while parsing", exception.getMessage());
    }

    @Test
    void test_invalidJson_invalid4() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/invalid4.json";
        
        var parser = new JsonParser(filePath);

        var exception = Assertions.assertThrows(ParsingException.class, () -> parser.parse());
        Assertions.assertEquals("Invalid literal: falseNotAConstant", exception.getMessage());
    }

    @Test
    void test_invalidJson_invalid5() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/invalid5.json";
        
        var parser = new JsonParser(filePath);

        var exception = Assertions.assertThrows(DuplicateKeyException.class, () -> parser.parse());
        Assertions.assertEquals("Duplicate key: value", exception.getMessage());
    }

    @Test
    void test_invalidJson_valid() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/valid.json";
        
        var parser = new JsonParser(filePath);

        try {
            var node = parser.parse();
            Assertions.assertEquals(JsonNodeType.JSON_OBJECT, node.getType());
            var obj = (JsonObject) node;
            Assertions.assertFalse(obj.isEmpty());
            Assertions.assertEquals(1, obj.size());
            Assertions.assertTrue(obj.containsKey("key"));

            var value = obj.get("key");
            Assertions.assertEquals(JsonNodeType.JSON_STRING, value.getType());
            Assertions.assertEquals("value", ((JsonString)value).value());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (InvalidJsonException e) {
            e.printStackTrace();
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_invalidJson_valid2() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step2/valid2.json";
        
        var parser = new JsonParser(filePath);

        try {
            var node = parser.parse();
            Assertions.assertEquals(JsonNodeType.JSON_OBJECT, node.getType());
            var obj = (JsonObject) node;
            Assertions.assertFalse(obj.isEmpty());
            Assertions.assertEquals(2, obj.size());
            Assertions.assertTrue(obj.containsKey("key"));

            var value = obj.get("key");
            Assertions.assertEquals(JsonNodeType.JSON_STRING, value.getType());
            Assertions.assertEquals("value", ((JsonString)value).value());

            Assertions.assertTrue(obj.containsKey("key"));

            value = obj.get("key2");
            Assertions.assertEquals(JsonNodeType.JSON_STRING, value.getType());
            Assertions.assertEquals("value", ((JsonString)value).value());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (InvalidJsonException e) {
            e.printStackTrace();
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
