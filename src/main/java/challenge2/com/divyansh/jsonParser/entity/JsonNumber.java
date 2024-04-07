package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.MethodNotSupportedException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

public class JsonNumber implements JsonNode {
    private Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }
    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_NUMBER;
    }

    @Override
    public JsonNode get(Object key) throws InvalidKeyException, MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    @Override
    public JsonSerializer serialize(JsonSerializer serializer) {
        serializer.append(value);
        return serializer;
    }

    @Override
    public Number value() {
        return this.value;
    }
}
