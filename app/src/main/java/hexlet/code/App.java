package hexlet.code;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(4).contains("dick");
        System.out.println(schema.isValid("dickHuevich"));
    }
}