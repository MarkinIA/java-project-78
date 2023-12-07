package hexlet.code.schemas;

import java.io.IOException;

public abstract class BaseSchema {
    public abstract boolean isValid(Object obj) throws IOException;
}
