package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.MethodNotSupportedException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

public class JsonString implements JsonNode {
    private String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_STRING;
    }

    @Override
    public JsonNode get(Object key) throws InvalidKeyException, MethodNotSupportedException {
        throw new MethodNotSupportedException();
    }

    @Override
    public JsonSerializer serialize(JsonSerializer serializer) {
        serializer
                .append("\"")
                .append(value)
                .append("\"");
        return serializer;
    }

    @Override
    public String value() {
        return this.value;
    }
}
