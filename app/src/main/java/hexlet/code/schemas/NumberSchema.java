package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema {
    public NumberSchema() {
        validationRules.add(v -> v instanceof Integer);
    }

    public NumberSchema required() {
        validationRules.add(v -> (!Objects.isNull(v)));
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        validationRules.add(v -> (Integer) v > 0);
        return this;
    }
    public NumberSchema range(int start, int end) {
        validationRules.add(v -> {
            Integer num = (Integer) v;
            return (num >= start) && (num <= end);
        });
        return this;
    }
}
