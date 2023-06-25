package cat.babot.data.elements;

import cat.babot.data.elements.base.DataBaseElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import com.microsoft.playwright.options.RequestOptions;

public class Comment implements DataBaseElement {
    private String id;
    @JsonProperty("post_id") private String postID;
    private String name;
    private String email;
    private String body;

    public Comment() {
        Faker faker = new Faker();
        setName(faker.name().fullName());
        setEmail(faker.internet().emailAddress());
        setBody(faker.howIMetYourMother().quote());
    }
    @Override public RequestOptions getCreationParams() {
        return RequestOptions.create()
                .setQueryParam("name", getName())
                .setQueryParam("email", getEmail())
                .setQueryParam("body", getBody());
    }

    @Override public String toString() {
        return "Comment {" +
                "id='" + id + '\'' +
                ", postID='" + postID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
