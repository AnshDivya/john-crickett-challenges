package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.MethodNotSupportedException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

public class JsonNull implements JsonNode {
    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_NULL;
    }

    @Override
    public JsonNode get(Object key) throws InvalidKeyException, MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    @Override
    public JsonSerializer serialize(JsonSerializer serializer) {
        serializer.append(null);
        return serializer;
    }

    @Override
    public Object value() {
        return null;
    }
}
