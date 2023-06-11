package cat.babot.tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends BaseTest {
    @Test
    public void testGetAPI() {
        //RequestOptions params = RequestOptions.create().("2789599");
        APIResponse response = manager.get("users/2789599");
        assertEquals(response.status(), 200);
        System.out.println(response.text());
        System.out.println(response.url());
    }
}
