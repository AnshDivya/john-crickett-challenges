package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.exception.MethodNotSupportedException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

public class JsonMember implements JsonNode {
    private String key;
    private JsonNode value;
    public JsonMember(String key, JsonNode value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_MEMBER;
    }

    @Override
    public JsonNode get(Object key) throws InvalidKeyException, MethodNotSupportedException {
        if(!(key instanceof String) || !key.equals(this.key)) {
            throw new InvalidKeyException(String.format(ErrorMessages.INVALID_KEY_MESSAGE, key, this.getType()));
        }
        return this.value;
    }

    public String getKey() {
        return this.key;
    }

    public JsonNode getValue() {
        return this.value;
    }

    @Override
    public JsonSerializer serialize(JsonSerializer serializer) {
        serializer
                .append("\"")
                .append(this.key)
                .append("\"")
                .append(Constants.COLON)
                .append(Constants.SPACE);
        return value.serialize(serializer);
    }

    @Override
    public Object value() {
        return null;
    }
}
