package cat.babot.data.elements;

import cat.babot.data.elements.base.DataBaseElement;
import cat.babot.data.utilities.Constants.TodoStatus;
import cat.babot.data.utilities.RandomEnumGenerator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import com.microsoft.playwright.options.RequestOptions;

import java.util.concurrent.TimeUnit;

public class Todo implements DataBaseElement {
    private String id;
    @JsonProperty("user_id") private String userID;
    private String title;
    @JsonProperty("due_on") private String dueOn;
    private TodoStatus status;

    public Todo() {
        Faker faker = new Faker();
        setDueOn(faker.date().future(1000, TimeUnit.DAYS).toString());
        setTitle(faker.book().title());
        setStatus((TodoStatus) new RandomEnumGenerator(TodoStatus.class).randomEnum());
    }

    @Override public RequestOptions getCreationParams() {
        return RequestOptions.create()
                .setQueryParam("title", getTitle())
                .setQueryParam("due_on", getDueOn())
                .setQueryParam("status", getStatus().toString());
    }

    @Override public String toString() {
        return "Todo {" +
                "id='" + id + '\'' +
                ", userID='" + userID + '\'' +
                ", title='" + title + '\'' +
                ", dueOn='" + dueOn + '\'' +
                ", status=" + status +
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

    public String getDueOn() {
        return dueOn;
    }

    public void setDueOn(String dueOn) {
        this.dueOn = dueOn;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
