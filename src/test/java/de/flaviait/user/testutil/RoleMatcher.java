package de.flaviait.user.testutil;

import de.flaviait.user.domain.Role;
import de.flaviait.user.domain.User;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

public class RoleMatcher extends TypeSafeMatcher<User> {

    private final String roleName;

    private RoleMatcher(String roleName) {
        Objects.requireNonNull(roleName);
        this.roleName = roleName;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("User has Role ").appendValue(roleName);
    }

    @Override
    protected void describeMismatchSafely(User item, Description mismatchDescription) {
        mismatchDescription.appendText("but had no role named ").appendValue(roleName);
    }

    public static RoleMatcher hasRole(String roleName) {
        return new RoleMatcher(roleName);
    }

    @Override
    protected boolean matchesSafely(User user) {
        if (user.getRoles() == null) {
            return false;
        }

        for (Role role : user.getRoles()) {
            if (roleName.equals(role.getName())) {
                return true;
            }
        }

        return false;
    }
}
