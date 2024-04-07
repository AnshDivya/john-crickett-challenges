package challenge2.com.divyansh.jsonParser.entity;

import challenge2.com.divyansh.jsonParser.exception.DuplicateKeyException;
import challenge2.com.divyansh.jsonParser.exception.InvalidKeyException;
import challenge2.com.divyansh.jsonParser.serializer.JsonSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonObject implements JsonNode {
    private Map<String, JsonNode> pairs;
    private List<JsonMember> memberList;

    public JsonObject() {
        this.pairs = new HashMap<>();
        this.memberList = new ArrayList<>();
    }
    @Override
    public JsonNodeType getType() {
        return JsonNodeType.JSON_OBJECT;
    }

    @Override
    public JsonNode get(Object key) throws InvalidKeyException {
        if(!(key instanceof String)) {
            throw new InvalidKeyException(String.format(ErrorMessages.INVALID_KEY_MESSAGE, key, this.getType()));
        }
        return this.pairs.getOrDefault(key.toString(), null);
    }

    public void addMember(String key, JsonMember node) throws DuplicateKeyException {
        if(this.pairs.containsKey(key)) {
            throw new DuplicateKeyException(String.format(ErrorMessages.DUPLICATE_KEY, key));
        }
        this.pairs.put(key, node.getValue());
        this.memberList.add(node);
    }

    public boolean isEmpty() {
        return this.pairs.size() == 0;
    }

    @Override
    public Object value() {
        return null;
    }

    @Override
    public JsonSerializer serialize(JsonSerializer serializer) {
        serializer
                .append(Constants.OPEN_OBJECT)
                .incIndent();

        int memberCount = 0;
        for(var member: memberList) {
            serializer
                    .newline()
                    .applyIndent();
            member.serialize(serializer);
            memberCount++;
            if(memberCount < size()) {
                serializer.addSeparator();
            }
        }

        serializer
                .newline()
                .decIndent()
                .applyIndent()
                .append(Constants.CLOSE_OBJECT);
        return serializer;
    }

    public Integer size() {
        return this.pairs.size();
    }

    public boolean containsKey(String key) {
        return this.pairs.containsKey(key);
    }
}
