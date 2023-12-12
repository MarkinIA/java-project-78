package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema {
    protected List<Predicate<Object>> validationRules = new ArrayList<>();
    protected boolean isRequired = false;

    public final boolean isValid(Object obj) {
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
