package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema {
    public List<Predicate<Object>> validationRules = new ArrayList<>();
    public boolean isRequired = false;
    public boolean isValid(Object obj) {
        if (isRequired || !Objects.isNull(obj)) {
            for (Predicate<Object> validation : validationRules) {
                if (!validation.test(obj)) {
                    return false;
                }
            }
        }
        return true;
    }
}
