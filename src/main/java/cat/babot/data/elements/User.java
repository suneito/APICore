package cat.babot.data.elements;

import cat.babot.data.elements.base.DataBaseElement;
import cat.babot.data.utilities.Constants.UserStatus;
import cat.babot.data.utilities.Constants.Gender;
import cat.babot.data.utilities.RandomEnumGenerator;
import com.github.javafaker.Faker;
import com.microsoft.playwright.options.RequestOptions;

public class User implements DataBaseElement {
    protected String id;
    private String email;
    private String name;
    private Gender gender;
    private UserStatus status;

    public User() {
        Faker faker = new Faker();
        setName(faker.name().fullName());
        setEmail(faker.internet().emailAddress());
        setGender((Gender) new RandomEnumGenerator(Gender.class).randomEnum());
        setStatus((UserStatus) new RandomEnumGenerator(UserStatus.class).randomEnum());
    }

    @Override public RequestOptions getCreationParams() {
        return RequestOptions.create()
                .setQueryParam("email", getEmail())
                .setQueryParam("name", getName())
                .setQueryParam("gender", getGender().toString())
                .setQueryParam("status", getStatus().toString());
    }

    @Override public String toString() {
        return "User {" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

}
