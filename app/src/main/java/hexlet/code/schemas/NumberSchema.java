package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Objects;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        validationRules = new ArrayList<>();
    }

    public NumberSchema required() {
        validationRules.add(v -> (!Objects.isNull(v) && v instanceof Integer));
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        validationRules.add(v -> (!(v instanceof Integer)
                || Integer.parseInt(v.toString()) >= 1));
        return this;
    }
    public NumberSchema range(int start, int end) {
        validationRules.add(v -> {
            if (v instanceof Integer) {
                int num = Integer.parseInt(v.toString());
                return (num >= start) && (num <= end);
            }
            return true;
        });
        return this;
    }
}
