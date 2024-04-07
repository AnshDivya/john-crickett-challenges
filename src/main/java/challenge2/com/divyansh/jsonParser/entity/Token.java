package challenge2.com.divyansh.jsonParser.entity;

public class Token {
    private TokenType type;
    private Object value;

    public Token(TokenType type) {
        this.type = type;
    }

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>", this.type, this.getValue() != null ? this.value.toString() : "EOF");
    }

    public String string() {
        return this.value.toString();
    }
}
