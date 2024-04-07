package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.MethodNotSupportedException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

public class JsonBoolean implements JsonNode {
    private Boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_BOOLEAN;
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
    public Boolean value() {
        return this.value;
    }
}
