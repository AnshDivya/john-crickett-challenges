package challenge2.com.divyansh.jsonParser.serializer;

public class JsonSerializer {
    private StringBuilder sb;
    private int indent;
    private char indentChar;
    private char separator;

    public JsonSerializer() {
        sb = new StringBuilder();
        this.indent = 0;
        this.indentChar = '\t';
        this.separator = ',';
    }

    public JsonSerializer(char indentChar, char separator) {
        this.sb = new StringBuilder();
        this.separator = separator;
        this.indentChar = indentChar;
        this.indent = 0;
    }

    public JsonSerializer incIndent() {
        this.indent++;
        return this;
    }

    public JsonSerializer decIndent() {
        if(this.indent > 0)
            this.indent--;
        return this;
    }

    public JsonSerializer addSeparator() {
        this.sb.append(this.separator);
        return this;
    }

    public JsonSerializer addIndent() {
        for(int i = 0; i < indent; i++) {
            this.sb.append(indentChar);
        }
        return this;
    }

    public JsonSerializer newline() {
        this.sb.append('\n');
        return this;
    }

    public JsonSerializer applyIndent() {
        for(int i = 0; i < indent; i++) {
            sb.append(indentChar);
        }
        return this;
    }

    public JsonSerializer append(char ch) {
        this.sb.append(ch);
        return this;
    }

    public JsonSerializer append(String str) {
        this.sb.append(str);
        return this;
    }

    public JsonSerializer append(Object obj) {
        this.sb.append(obj);
        return this;
    }

    @Override
    public String toString() {
        return this.sb.toString();
    }
}
