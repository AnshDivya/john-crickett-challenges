package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.MethodNotSupportedException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

public interface JsonNode {
    JsonNodeType getType();
    JsonNode get(Object key) throws InvalidKeyException, MethodNotSupportedException;
    Object value();
    JsonSerializer serialize(JsonSerializer serializer);
}
