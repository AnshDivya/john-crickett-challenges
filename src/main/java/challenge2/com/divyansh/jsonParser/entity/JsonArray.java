package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements JsonNode {

    List<JsonNode> list;

    public JsonArray() {
        this.list = new ArrayList<>();
    }
    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_ARRAY;
    }

    @Override
    public JsonNode get(Object key) throws InvalidKeyException {
        if(!(key instanceof Integer)) {
            throw new InvalidKeyException(String.format(ErrorMessages.INVALID_KEY_MESSAGE, key, this.getType()));
        }
        var intKey = (Integer) key;

        if(intKey >= list.size()) {
            throw new InvalidKeyException(String.format(ErrorMessages.INVALID_INDEX_MESSAGE, intKey));
        }
        return this.list.get(intKey);
    }

    public void addNode(JsonNode node) {
        this.list.add(node);
    }

    @Override
    public Object value() {
        return null;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public JsonSerializer serialize(JsonSerializer serializer) {
        serializer
                .append(Constants.OPEN_ARRAY)
                .incIndent();

        for(int i = 0; i < list.size(); i++) {
            serializer
                    .newline()
                    .applyIndent();

            list.get(i).serialize(serializer);
            if(i < list.size() - 1) {
                serializer.addSeparator();
            }
        }

        return serializer
                .newline()
                .decIndent()
                .applyIndent()
                .append(Constants.CLOSE_ARRAY);
    }

    public int size() {
        return this.list.size();
    }
}
