package hexlet.code;

import java.io.IOException;

@FunctionalInterface
public interface ValidationInterface {
    boolean validateData(Object obj) throws IOException;
}
