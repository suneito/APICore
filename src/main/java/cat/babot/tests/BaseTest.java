package cat.babot.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import cat.babot.manager.RequestManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    private static final String API_TOKEN = "Bearer 415448662ebeffc1317dddd6079940caf9bcb9a2246b1eb54ffa31521aaab604";
    protected static RequestManager manager;
    protected static Logger atenea;

    @BeforeAll
    public static void setup() {
        manager = new RequestManager();
        manager.createPlaywright();
        atenea = Logger.getLogger("APICore");
        String baseUrl = "https://gorest.co.in/public/v2/";
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        headers.put("Authorization", API_TOKEN);
        manager.setApiRequestContext(baseUrl, headers);
    }

    @AfterAll
    public static void tearDown() {
        manager.disposeAPIRequestContext();
        manager.closePlaywright();
    }
}