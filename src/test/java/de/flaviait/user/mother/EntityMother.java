package de.flaviait.user.mother;

import de.flaviait.user.entity.RoleEntity;
import de.flaviait.user.entity.UserEntity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EntityMother {

    private static final String DEFAULT_FIRST_NAME = "Peter";
    private static final String DEFAULT_SUR_NAME = "Meier";
    private static final String DEFAULT_EMAIL= "test@example.com";
    private static final String ROLE_PREFIX = "ROLE_";

    private final AtomicLong ids;

    public EntityMother() {
        ids = new AtomicLong();
    }

    public EntityMother(AtomicLong ids) {
        this.ids = ids;
    }

    public UserEntity givenAnUnpersistedUser() {
        UserEntity user = new UserEntity();
        return defaultsFor(user);
    }

    public UserEntity givenAPersistedUser() {
        UserEntity entity = new UserEntity();
        entity.setId(nextId());
        return defaultsFor(entity);
    }

    public RoleEntity givenAPersistedRole() {
        RoleEntity role = givenAnUnpersistedRole();
        role.setId(nextId());
        return role;
    }

    public RoleEntity givenAnUnpersistedRole() {
        RoleEntity role = new RoleEntity();
        return defaultsFor(role);
    }

    private RoleEntity defaultsFor(RoleEntity role) {
        role.setName(randomRoleName());
        return role;
    }

    private UserEntity defaultsFor(UserEntity user) {
        user.setFirstName(DEFAULT_FIRST_NAME);
        user.setSurName(DEFAULT_SUR_NAME);
        user.setEmail(DEFAULT_EMAIL);
        user.setActive(false);

        return user;
    }

    private long nextId() {
        return ids.getAndIncrement();
    }

    private String randomRoleName() {
        return ROLE_PREFIX + UUID.randomUUID().toString();
    }
}
