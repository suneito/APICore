package cat.babot.tests;

import cat.babot.data.utilities.Constants;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import java.util.logging.Level;

public class BaseCalls extends BaseTest {
    //GET
    public APIResponse find(Constants.Element element, String id) {
        APIResponse response = manager.get(element.toString().concat(id));
        atenea.log(Level.INFO, String.format("Get call for find %s with id '%s' got response {0}", element.name(), id), response.status());
        return response;
    }

    //POST
    public APIResponse create(Constants.Element element, String id, RequestOptions params) {
        APIResponse response;
        if (element.equals(Constants.Element.USER)) {
            response = manager.post(element.toString(), params);
        } else if (element.equals(Constants.Element.COMMENT)) {
            response = manager.post(Constants.Element.POST + id + '/' + element, params);
        } else {
            response = manager.post(Constants.Element.USER + id + '/' + element, params);
        }
        atenea.log(Level.INFO, String.format("Post call for create %s got response {0}", element.name()), response.status());
        return response;
    }

    //PUT
    public APIResponse update(Constants.Element element, String id, RequestOptions params) {
        APIResponse response = manager.put(element.toString() + id, params);
        atenea.log(Level.INFO, String.format("Put call for update %s with id '%s' got response {0}", element.name(), id), response.status());
        return response;
    }

    //DELETE
    public APIResponse delete(Constants.Element element, String id) {
        APIResponse response = manager.delete(element.toString() + id );
        atenea.log(Level.INFO, String.format("Delete call for delete %s with id '%s' got response {0}", element.name(), id), response.status());
        return response;
    }
}
