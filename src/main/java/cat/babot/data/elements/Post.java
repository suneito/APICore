package cat.babot.data.elements;

import cat.babot.data.elements.base.DataBaseElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import com.microsoft.playwright.options.RequestOptions;

public class Post implements DataBaseElement {
    private String id;
    @JsonProperty("user_id") private String userID;
    private String title;
    private String body;

    public Post() {
        Faker faker = new Faker();
        setTitle(faker.lorem().sentence());
        setBody(faker.friends().quote());
    }

    @Override public RequestOptions getCreationParams() {
        return RequestOptions.create()
                .setQueryParam("title", getTitle())
                .setQueryParam("body", getBody());
    }

    @Override public String toString() {
        return "Post {" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
