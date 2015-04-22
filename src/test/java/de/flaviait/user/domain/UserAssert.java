package de.flaviait.user.domain;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class UserAssert extends AbstractAssert<UserAssert, User> {

    private UserAssert(User actual) {
        super(actual, UserAssert.class);
    }

    public static UserAssert userAssert(User user) {
        return new UserAssert(user);
    }

    public UserAssert hasId() {
        isNotNull();
        Assertions.assertThat(actual.getId()).isNotNull();
        return this;
    }

    public UserAssert hasEmail(String email) {
        isNotNull();
        Assertions.assertThat(actual.getEmail()).isEqualTo(email);
        return this;
    }

    public UserAssert hasFirstName(String firstName) {
        isNotNull();
        Assertions.assertThat(actual.getFirstName()).isEqualTo(firstName);
        return this;
    }

    public UserAssert hasSurName(String lastName) {
        isNotNull();
        Assertions.assertThat(actual.getSurName()).isEqualTo(lastName);
        return this;
    }

    public UserAssert hasNoPassword() {
        isNotNull();
        Assertions.assertThat(actual.getPassword()).isNull();
        return this;
    }

    public UserAssert isActiveUser() {
        isNotNull();
        Assertions.assertThat(actual.isActive()).isEqualTo(Boolean.TRUE);
        return this;
    }
}
