package cat.babot.data.utilities;

import cat.babot.data.elements.Comment;
import cat.babot.data.elements.Post;
import cat.babot.data.elements.Todo;
import cat.babot.data.elements.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Constants {
    public enum Element {
        POST("posts/", Post.class),
        USER("users/", User.class),
        TODO("todos/", Todo.class),
        COMMENT("comments/", Comment.class);

        private final String route;
        private final Class<?> associatedClass;

        Element(String route, Class<?> associatedClass) {
            this.route = route;
            this.associatedClass = associatedClass;
        }

        @Override public String toString() {
            return route;
        }

        public Class<?> getAssociatedClass() {
            return associatedClass;
        }
    }

    public enum Gender {
        @JsonProperty("male") M ("male"),
        @JsonProperty("female") F ("female");

        final String value;

        Gender(String gender) {
            value = gender;
        }

        @Override public String toString() {
            return value;
        }
    }

    public enum UserStatus {
        @JsonProperty("active") ACT ("active"),
        @JsonProperty("inactive") INA ("inactive");

        final String value;

        UserStatus(String status) {
            value = status;
        }

        @Override public String toString() {
            return value;
        }
    }

    public enum TodoStatus {
        @JsonProperty("pending") PEN ("pending"),
        @JsonProperty("completed") COM ("completed");

        final String value;

        TodoStatus(String status) {
            value = status;
        }

        @Override public String toString() {
            return value;
        }
    }
}
