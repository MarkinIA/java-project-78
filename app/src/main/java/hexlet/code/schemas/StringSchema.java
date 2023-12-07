package hexlet.code.schemas;

import hexlet.code.ValidationInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringSchema extends BaseSchema {

    private final List<ValidationInterface> validationRules = new ArrayList<>();

    private static List<String> contains = new ArrayList<>();

    public StringSchema() {
    }
    public StringSchema required() {
        validationRules.add(p -> (!Objects.isNull(p) && p instanceof String
                && !String.valueOf(p).isEmpty()));
        return this;
    }

    public StringSchema minLength(int length) {
        validationRules.add(p -> (String.valueOf(p).length() >= length));
        return this;
    }

    public StringSchema contains(String str) {
        StringSchema.contains.add(str);
        validationRules.add(p -> {
            for (String line : contains) {
                if (!String.valueOf(p).contains(line)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    public boolean isValid(Object obj) throws IOException {
        for (ValidationInterface validation : validationRules) {
            if (!validation.validateData(obj)) {
                return false;
            }
        }
        return true;
    }
}
