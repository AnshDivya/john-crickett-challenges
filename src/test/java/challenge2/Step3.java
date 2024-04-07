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

public class Step3 {
    @Test
    void test_invalidJson_invalid() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step3/invalid.json";
        
        var parser = new JsonParser(filePath);

        var exception = Assertions.assertThrows(ParsingException.class, () -> parser.parse());
        Assertions.assertEquals("Encountered an invalid character F while parsing", exception.getMessage());
    }

    @Test
    void test_invalidJson_valid() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step3/valid.json";
        
        var parser = new JsonParser(filePath);

        try {
            var node = parser.parse();
            Assertions.assertEquals(JsonNodeType.JSON_OBJECT, node.getType());
            var obj = (JsonObject) node;
            Assertions.assertFalse(obj.isEmpty());
            Assertions.assertEquals(6, obj.size());

            Assertions.assertTrue(obj.containsKey("key1"));
            var value = obj.get("key1");
            Assertions.assertEquals(JsonNodeType.JSON_BOOLEAN, value.getType());
            Assertions.assertEquals(true, ((JsonBoolean)value).value());

            Assertions.assertTrue(obj.containsKey("key2"));
            value = obj.get("key2");
            Assertions.assertEquals(JsonNodeType.JSON_BOOLEAN, value.getType());
            Assertions.assertEquals(false, ((JsonBoolean)value).value());

            Assertions.assertTrue(obj.containsKey("key3"));
            value = obj.get("key3");
            Assertions.assertEquals(JsonNodeType.JSON_NULL, value.getType());
            Assertions.assertNull(((JsonNull) value).value());

            Assertions.assertTrue(obj.containsKey("key4"));
            value = obj.get("key4");
            Assertions.assertEquals(JsonNodeType.JSON_STRING, value.getType());
            Assertions.assertEquals("value", ((JsonString)value).value());

            Assertions.assertTrue(obj.containsKey("key5"));
            value = obj.get("key5");
            Assertions.assertEquals(JsonNodeType.JSON_NUMBER, value.getType());
            Assertions.assertEquals(101.0, ((JsonNumber)value).value());

            Assertions.assertTrue(obj.containsKey("key6"));
            value = obj.get("key6");
            Assertions.assertEquals(JsonNodeType.JSON_ARRAY, value.getType());
            var array = (JsonArray) value;
            Assertions.assertEquals(3, array.size());
            Assertions.assertEquals("222", array.get(0).value());
            Assertions.assertEquals("234234", array.get(1).value());
            Assertions.assertNull(array.get(2).value());


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
