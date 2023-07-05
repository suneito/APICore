package cat.babot.tests;

import cat.babot.data.elements.Comment;
import cat.babot.data.elements.Post;
import cat.babot.data.elements.Todo;
import cat.babot.data.elements.User;
import cat.babot.datamanager.translator.Translator;
import cat.babot.data.utilities.Constants.Element;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends BaseCalls {
    @Test
    public void testE1() {
//        Crea usuari
        User userARC = callCreate(Element.USER, null, new User().getCreationParams());
//        Crea post
        Post postARC = callCreate(Element.POST, userARC.getId(), new Post().getCreationParams());
//        Crea comentari fail
        callFailCreate(Element.COMMENT, "1234", new Comment().getCreationParams());
//        Crea comentari
        Comment commentARC = callCreate(Element.COMMENT, postARC.getId(), new Comment().getCreationParams());
//        Modifica comentari
        Comment commentTBC = callUpdate(Element.COMMENT, commentARC.getId(), new Comment().getCreationParams());
        assertEquals(commentARC.getId(), commentTBC.getId());
//        Cerca comentari
        Comment commentTBF = callFind(Element.COMMENT, commentARC.getId());
        assertEquals(commentTBC.toString(), commentTBF.toString());
//        Elimina comentari
        callDelete(Element.COMMENT, commentARC.getId());
//        Cerca comentari fail
        callFailFind(Element.COMMENT, commentARC.getId());
    }


    @Test
    public void testE2() {
//        Cerca usuari fail
        callFailFind(Element.COMMENT, "1234");
//        Crea usuari
        User userARC = callCreate(Element.USER, null, new User().getCreationParams());
//        Crea post
        callFailCreate(Element.POST, "1234", new Post().getCreationParams());
//        Crea post
        Post postARC = callCreate(Element.POST, userARC.getId(), new Post().getCreationParams());
//        Modifica post
        Post postTBC = callUpdate(Element.POST, postARC.getId(), new Post().getCreationParams());
        assertEquals(postARC.getId(), postTBC.getId());
//        Cerca post
        callFind(Element.POST, postTBC.getId());
        assertEquals(postARC.getId(), postTBC.getId());
//        Crea comentari
        Comment commentARC = callCreate(Element.COMMENT, postARC.getId(), new Comment().getCreationParams());
//        Elimina post
        callDelete(Element.POST, postTBC.getId());
//        Cerca comentari
        callFailFind(Element.COMMENT, commentARC.getId());
//        Cerca post
        callFailFind(Element.POST, postTBC.getId());

    }

    @Test
    public void testE3() {
//        Crea usuari
        callFailCreate(Element.USER, null, new Post().getCreationParams());
//        Crea usuari
        User userARC = callCreate(Element.USER, null, new User().getCreationParams());
//        Crea toddo
        callFailCreate(Element.TODO, "1234", new Todo().getCreationParams());
//        Crea toddo
        Todo todoARC = callCreate(Element.TODO, userARC.getId(), new Todo().getCreationParams());
//        Modifica toddo
        Todo todoTBC = callUpdate(Element.TODO, todoARC.getId(), new Todo().getCreationParams());
        assertEquals(todoTBC.getId(), todoARC.getId());
//        Cerca toddo
        callFind(Element.TODO, todoTBC.getId());
//        Elimina toddo
        callDelete(Element.TODO, todoTBC.getId());
//        Cerca toddo
        callFailFind(Element.TODO, todoTBC.getId());
//        Elimina usuari
        callDelete(Element.USER, userARC.getId());
//        Cerca usuari
        callFailFind(Element.USER, userARC.getId());


    }


    private void callFailCreate(Element element, String id, RequestOptions params) {
        APIResponse response = create(element, id, params);
        assertEquals(response.status(), 422);
    }

    private  <T> T callCreate(Element element, String id, RequestOptions params) {
        APIResponse response = create(element, id, params);
        assertEquals(response.status(), HttpURLConnection.HTTP_CREATED);
        return (T) new Translator().jsonToObject(response.text(), element.getAssociatedClass());
    }

    private <T> T callUpdate(Element element, String id, RequestOptions params) {
        APIResponse response = update(element, id, params);
        assertEquals(response.status(), HttpURLConnection.HTTP_OK);
        return (T) new Translator().jsonToObject(response.text(), element.getAssociatedClass());
    }

    private <T> T callFind(Element element, String id) {
        APIResponse response = find(element, id);
        assertEquals(response.status(), HttpURLConnection.HTTP_OK);
        return (T) new Translator().jsonToObject(response.text(), element.getAssociatedClass());
    }

    private void callFailFind(Element element, String id) {
        APIResponse response = find(element, id);
        assertEquals(response.status(), HttpURLConnection.HTTP_NOT_FOUND);
    }

    private void callDelete(Element element, String id) {
        APIResponse response = delete(element, id);
        assertEquals(response.status(), 204);
    }
}
