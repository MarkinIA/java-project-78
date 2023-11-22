package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringSchema extends BaseSchema {

    private static boolean required;
    private static boolean pickedMinLength;
    private static int minLength;

    private static List<String> contains = new ArrayList<>();
    public StringSchema() {
        required = false;
        pickedMinLength = false;
    }
    public StringSchema required() {
        StringSchema.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        pickedMinLength = true;
        StringSchema.minLength = length;
        return this;
    }

    public StringSchema contains(String str) {
        StringSchema.contains.add(str);
        return this;
    }

    public boolean isValid(Object obj) {
        return checkRequired(obj) && checkContent(obj) && checkLength(obj);
    }

    public boolean checkRequired(Object obj) {
        return !required || (!Objects.isNull(obj) && obj instanceof String
                && !String.valueOf(obj).isEmpty());
    }

    public boolean checkLength(Object obj) {
        return !pickedMinLength || String.valueOf(obj).length() >= minLength;
    }

    public boolean checkContent(Object obj) {
        if (!contains.isEmpty()) {
            for (String line : contains) {
                if (!String.valueOf(obj).contains(line)) {
                    return false;
                }
            }
        }
        return true;
    }
}
