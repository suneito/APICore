package cat.babot.manager;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.util.Map;

public class RequestManager {
    private Playwright playwright;
    private APIRequestContext apiRequestContext;

    public void createPlaywright() {
        playwright = Playwright.create();
    }

    public void setApiRequestContext(String baseUrl, Map<String, String> headers) {
        apiRequestContext = playwright.request()
                .newContext(new APIRequest.NewContextOptions().setBaseURL(baseUrl)
                        .setExtraHTTPHeaders(headers));
    }

    //GET
    public APIResponse get(String endpoint) {
        return apiRequestContext.get(endpoint);
    }

    public APIResponse get(String endpoint, RequestOptions options) {
        return apiRequestContext.get(endpoint, options);
    }

    //POST
    public APIResponse post(String endpoint) {
        return apiRequestContext.post(endpoint);
    }

    public APIResponse post(String endpoint, RequestOptions options) {
        return apiRequestContext.post(endpoint, options);
    }

    //PUT
    public APIResponse put(String endpoint) {
        return apiRequestContext.put(endpoint);
    }

    public APIResponse put(String endpoint, RequestOptions options) {
        return apiRequestContext.put(endpoint, options);
    }

    //PATCH
    public APIResponse patch(String endpoint) {
        return apiRequestContext.patch(endpoint);
    }

    public APIResponse patch(String endpoint, RequestOptions options) {
        return apiRequestContext.patch(endpoint, options);

    }

    //DELETE
    public APIResponse deleteRequest(String endpoint) {
        return apiRequestContext.delete(endpoint);
    }

    public APIResponse deleteRequest(String endpoint, RequestOptions options) {
        return apiRequestContext.delete(endpoint, options);
    }

    public void disposeAPIRequestContext() {
        apiRequestContext.dispose();
    }

    public void closePlaywright() {
        playwright.close();
    }

}
