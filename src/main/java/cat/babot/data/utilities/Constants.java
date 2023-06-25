package cat.babot.data.utilities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Constants {
    public enum Element {
        POST("posts/"),
        USER("users/"),
        TODO("todos/"),
        COMMENT("comments/");

        final String value;

        Element(String element) {
            value = element;
        }

        @Override public String toString() {
            return value;
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
