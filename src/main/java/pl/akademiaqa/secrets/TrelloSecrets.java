package pl.akademiaqa.secrets;

public class TrelloSecrets {

    private TrelloSecrets() {
    }

    private static final String KEY = "YOUR_KEY";
    private static final String TOKEN = "YOUR_TOKEN";

    public static String getKey() {
        return KEY;
    }

    public static String getToken() {
        return TOKEN;
    }
}
