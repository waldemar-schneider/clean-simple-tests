package de.flaviait.user.mother;

import de.flaviait.user.domain.Role;
import de.flaviait.user.domain.User;
import de.flaviait.user.entity.RoleEntity;
import de.flaviait.user.entity.UserEntity;

import java.util.Objects;

public class DomainMother {

    private EntityMother entities = new EntityMother();

    public User givenAPersistedUser() {
        UserEntity user = entities.givenAPersistedUser();
        return domainObjectFor(user);
    }

    public User givenAnUnpersistedUser() {
        UserEntity user = entities.givenAnUnpersistedUser();
        return domainObjectFor(user);
    }

    public Role givenAPersistedRole() {
        RoleEntity role = entities.givenAPersistedRole();
        return domainObjectFor(role);
    }

    public Role givenAnUnpersistedRole() {
        RoleEntity role = entities.givenAnUnpersistedRole();
        return domainObjectFor(role);
    }

    public static void supply(User user, Role role) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(role);

        user.addRole(role);
    }

    private User domainObjectFor(UserEntity userEntity) {
        return new User(userEntity);
    }

    private Role domainObjectFor(RoleEntity role) {
        return new Role(role);
    }

    public static <T> T with(T t) {
        return t;
    }
}
