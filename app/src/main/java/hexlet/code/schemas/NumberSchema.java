package hexlet.code.schemas;

import hexlet.code.ValidationInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NumberSchema extends BaseSchema {
    private List<ValidationInterface> validationRules;
    private List<Integer> range = new ArrayList<>();
    public NumberSchema() {
        validationRules = new ArrayList<>();
    }

    public NumberSchema required() {
        validationRules.add(v -> (!Objects.isNull(v) && v instanceof Integer));
        return this;
    }

    public NumberSchema positive() {
        validationRules.add(v -> (Objects.isNull(v) || Integer.parseInt(v.toString()) >= 1));
        return this;
    }
    public NumberSchema range(int start, int end) {
        this.range = List.of(start, end);
        validationRules.add(v -> {
            int num = Integer.parseInt(v.toString());
            return (num >= range.get(0)) && (num <= range.get(1));
        });
        return this;
    }

    public boolean isValid(Object obj) {
        for (ValidationInterface validation : validationRules) {
            if(!validation.validateData(obj)) {
                return false;
            }
        }
        return true;
    }

}
