package de.flaviait.user.domain;

import com.google.common.base.Preconditions;
import de.flaviait.user.entity.RoleEntity;
import de.flaviait.user.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserRegistrar {

    public static Role ROLE_USER = new Role("ROLE_USER");
    public static Role ROLE_ADMIN = new Role("ROLE_ADMIN");

    private AtomicLong ids = new AtomicLong(1L);

    Map<Long, User> registration = new HashMap<>();

    public User registerUser(User user) {
        validateUser(user);

        generateId(user);
        addUserRole(user);
        registerInteral(user);
        activateUser(user);

        return user;
    }

    private void generateId(User user) {
        user.setId(nextId());
    }

    private void registerInteral(User user) {
        registration.put(user.getId(), user);
    }

    private void activateUser(User user) {
        user.setActive(true);
    }

    public User registerAdmin(User user) {
        validateUser(user);

        generateId(user);
        addUserRole(user);
        addAdminRole(user);
        registerInteral(user);

        return user;
    }

    public void notImplemented() {
        throw new RuntimeException("not implemented yet");
    }

    private void addUserRole(User user) {
        user.addRole(ROLE_USER);
    }

    private void addAdminRole(User user) {
        user.addRole(ROLE_ADMIN);
    }

    private void validateUser(User user) {
        Preconditions.checkNotNull(user, "user must not be null to be registred");
        Preconditions.checkNotNull(user.getFirstName(), "user firstname must not be null to be registred");
        Preconditions.checkNotNull(user.getSurName(), "user surname must not be null to be registred");
        Preconditions.checkNotNull(user.getEmail(), "user email must not be null to be registred");
        Preconditions.checkArgument(user.getId() == null);
        Preconditions.checkNotNull(user.getRoles());
    }

    private Long nextId() {
        return ids.getAndIncrement();
    }

    public User findByEmail(String email) {
        Preconditions.checkNotNull(email);

        User user = registration.get(email);

        if (user == null) {
            throw new NotFoundException("no suitable user found for this email");
        }

        return null;
    }
}
