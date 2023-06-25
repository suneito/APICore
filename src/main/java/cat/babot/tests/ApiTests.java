package cat.babot.tests;

import cat.babot.data.elements.Comment;
import cat.babot.data.elements.Post;
import cat.babot.data.elements.Todo;
import cat.babot.data.elements.User;
import cat.babot.data.translator.Translator;
import cat.babot.data.utilities.Constants.Element;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends BaseTest {
    @Test
    public void testGetAPI() {
        //TODO implement loger, methods high layer
        APIResponse response;
        response = find(Element.USER, "3250300");
        System.out.println(response.text());
        System.out.println(response.url());
        assertEquals(response.status(), 200);

        response = find(Element.TODO, "18665");
        System.out.println(response.text());
        System.out.println(response.url());
        assertEquals(response.status(), 200);

        response = find(Element.POST, "47415");
        System.out.println(response.text());
        System.out.println(response.url());
        assertEquals(response.status(), 200);

        response = find(Element.COMMENT, "42373");
        System.out.println(response.text());
        System.out.println(response.url());
        assertEquals(response.status(), 200);

        response = create(Element.USER, null, new User().getCreationParams());
        System.out.println(response.text());
        User user = new Translator().jsonToObject(response.text(), User.class);
        System.out.println(user.toString());
        assertEquals(response.status(), 201);

        response = create(Element.POST, user.getId(), new Post().getCreationParams());
        System.out.println(response.text());
        Post post = new Translator().jsonToObject(response.text(), Post.class);
        System.out.println(post.toString());
        assertEquals(response.status(), 201);

        response = create(Element.TODO, user.getId(), new Todo().getCreationParams());
        System.out.println(response.text());
        Todo todo = new Translator().jsonToObject(response.text(), Todo.class);
        System.out.println(todo.toString());
        assertEquals(response.status(), 201);

        response = create(Element.COMMENT, post.getId(), new Comment().getCreationParams());
        System.out.println(response.text());
        Comment comment = new Translator().jsonToObject(response.text(), Comment.class);
        System.out.println(comment.toString());
        assertEquals(response.status(), 201);

        response = update(Element.USER, user.getId(), new User().getCreationParams());
        System.out.println(response.text());
        User userUpdated = new Translator().jsonToObject(response.text(), User.class);
        System.out.println(user.toString());
        System.out.println(userUpdated.toString());
        assertEquals(response.status(), 200);

        response = delete(Element.USER, user.getId());
        System.out.println(response.text());
        assertEquals(response.status(), 204);

        response = find(Element.USER, user.getId());
        System.out.println(response.text());
        assertEquals(response.status(), 404);
    }
    //GET
    public APIResponse find(Element element, String id) {
        return manager.get(element.toString().concat(id));
    }

    //POST
    public APIResponse create(Element element, String id, RequestOptions params) {
        if (element.equals(Element.USER)) return manager.post(element.toString(), params);
        if (element.equals(Element.COMMENT)) return manager.post(Element.POST + id + '/' + element, params);
        return manager.post(Element.USER + id + '/' + element, params);
    }

    //PUT
    public APIResponse update(Element element, String id, RequestOptions params) {
        return manager.put(element.toString() + id, params);
    }

    //DELETE
    public APIResponse delete(Element element, String id) {
        return manager.delete(element.toString() + id );
    }
}
