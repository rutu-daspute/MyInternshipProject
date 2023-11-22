import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class URLShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public URLShortener() {
        shortToLongMap = new HashMap<>();
        longToShortMap = new HashMap<>();
    }

    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return longToShortMap.get(longURL);
        }

        String shortURL = generateShortURL();
        shortToLongMap.put(shortURL, longURL);
        longToShortMap.put(longURL, shortURL);

        return shortURL;
    }

    public String expandURL(String shortURL) {
        return shortToLongMap.getOrDefault(shortURL, "URL not found");
    }

    private String generateShortURL() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();

        String longURL = "https://www.example.com";
        String shortURL = urlShortener.shortenURL(longURL);

        System.out.println("Shortened URL: " + shortURL);
        System.out.println("Expanded URL: " + urlShortener.expandURL(shortURL));
    }
}