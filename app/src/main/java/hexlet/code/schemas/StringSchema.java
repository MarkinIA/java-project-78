package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class StringSchema extends BaseSchema {
    private static List<String> contains = new ArrayList<>();

    public StringSchema() {
    }
    public StringSchema required() {
        validationRules.add(p -> (!Objects.isNull(p) && p instanceof String
                && !String.valueOf(p).isEmpty()));
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        validationRules.add(p -> (String.valueOf(p).length() >= length));
        return this;
    }

    public StringSchema contains(String str) {
        contains.clear();
        contains.add(str);
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
}
