package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public class StringSchema{

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

    public boolean isValid(Object obj) {
        String str = obj instanceof String ? String.valueOf(obj) : "";
        if (required && str.isEmpty()) {
            return false;
        } else if (minLength != 0 && str.length() < minLength) {
            return false;
        } else if (!contains.isEmpty()) {
            for (String line : contains) {
                if (!str.contains(line)) {
                    return false;
                }
            }
        }
        return true;
    }

}
