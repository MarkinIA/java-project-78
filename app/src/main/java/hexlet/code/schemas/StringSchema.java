package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringSchema extends BaseSchema {

    private boolean required;
    private int minLength;

    private List<String> contains = new ArrayList<>();
    public StringSchema() {

    }
    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public StringSchema contains(String str) {
        this.contains.add(str);
        return this;
    }

    //!!TEST SEGMENT!!!
    public boolean getRequired() {
        return required;
    }

    public int getMinLength() {
        return minLength;
    }

    public List<String> getContains() {
        return contains;
    }

    //!!END OF A TEST SEGMENT!!!

    @Override
    public boolean isValid(Object obj) {
        //String str = obj instanceof String ? String.valueOf(obj) : "";
        if (required && (Objects.isNull(obj) || !(obj instanceof String))) {
            return false;
        } else if (minLength != 0 && String.valueOf(obj).length() < minLength) {
            return false;
        } else if (!contains.isEmpty()) {
            for (String line : contains) {
                if (!String.valueOf(obj).contains(line)) {
                    return false;
                }
            }
        }
        return true;
    }
}
