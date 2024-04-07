package challenge2;

import challenge2.com.divyansh.jsonParser.entity.ErrorMessages;
import challenge2.com.divyansh.jsonParser.entity.JsonNodeType;
import challenge2.com.divyansh.jsonParser.entity.JsonObject;
import challenge2.com.divyansh.jsonParser.exception.DuplicateKeyException;
import challenge2.com.divyansh.jsonParser.exception.InvalidJsonException;
import challenge2.com.divyansh.jsonParser.exception.ParsingException;
import challenge2.com.divyansh.jsonParser.lexer.Lexer;
import challenge2.com.divyansh.jsonParser.parser.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class Step1 {

    @Test
    void test_validJson_Empty() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step1/valid.json";
        
        var parser = new JsonParser(filePath);

        try {
            var node = parser.parse();
            Assertions.assertEquals(node.getType(), JsonNodeType.JSON_OBJECT);
            var obj = (JsonObject) node;
            Assertions.assertEquals(obj.isEmpty(), true);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (InvalidJsonException e) {
            e.printStackTrace();
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_invalidJson_Empty() {
        var filePath = "/home/anshdivya/IdeaProjects/john-crickett-challenges/src/test/resources/challenge2/tests/step1/invalid.json";
        
        var parser = new JsonParser(filePath);
        var exception = Assertions.assertThrows(InvalidJsonException.class, () -> parser.parse());
        Assertions.assertEquals("Unexpected Token: EOF", exception.getMessage());
    }
}
