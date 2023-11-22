package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NumberSchema {
    private boolean required = false;
    private boolean positive = false;
    private List<Integer> range = new ArrayList<>();
    public NumberSchema() {

    }

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }
    public NumberSchema range(int start, int end) {
        this.range = List.of(start, end);
        return this;
    }

    public boolean isValid(Object obj) {
        return checkRequired(obj) && checkPositive(obj) && checkRange(obj);
    }

    public boolean checkRequired(Object obj) {
        return !required || (!Objects.isNull(obj) && obj instanceof Integer);
    }

    public boolean checkPositive(Object obj) {
        return !positive || (Objects.isNull(obj) || Integer.parseInt(obj.toString()) >= 1);
    }

    public boolean checkRange(Object obj) {
        if (!range.isEmpty()) {
            int num = Integer.parseInt(obj.toString());
            if (!((num >= range.get(0)) && (num <= range.get(1)))) {
                return false;
            }
        }
        return true;
    }
}
