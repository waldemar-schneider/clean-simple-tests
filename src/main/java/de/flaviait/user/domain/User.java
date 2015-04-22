package de.flaviait.user.domain;

import com.google.common.collect.Lists;
import de.flaviait.user.entity.RoleEntity;
import de.flaviait.user.entity.UserEntity;

import javax.management.openmbean.TabularData;
import java.util.List;
import java.util.Objects;

public class User {

    private final UserEntity entity;

    private List<Role> roles;

    public User(UserEntity entity) {
        this.entity = entity;
        this.roles = initializeRoles(entity);
    }

    public Long getId() {
        return entity.getId();
    }

    public void setId(Long id) {
        entity.setId(id);
    }

    public String getFirstName() {
        return entity.getFirstName();
    }

    public void setFirstName(String firstName) {
        entity.setFirstName(firstName);
    }

    public String getSurName() {
        return entity.getSurName();
    }

    public void setSurName(String surName) {
        entity.setSurName(surName);
    }

    public String getEmail() {
        return entity.getEmail();
    }

    public void setEmail(String email) {
        entity.setEmail(email);
    }

    public String getPassword() {
        return entity.getPassword();
    }

    public void setPassword(String password) {
        entity.setPassword(password);
    }

    public boolean isActive() {
        return entity.isActive();
    }

    public void setActive(boolean active) {
        entity.setActive(active);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private List<Role> initializeRoles(UserEntity user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getRoles());

        List<Role> roles = Lists.newArrayList();

        for (RoleEntity roleEntity : user.getRoles()) {
            Role role = new Role(roleEntity);
            roles.add(role);
        }

        return roles;
    }

    public void addRole(Role userRole) {
        ensureValidRole();

        roles.add(userRole);
    }

    private void ensureValidRole() {
        if (roles == null) {
            roles = Lists.newArrayList();
        }
    }
}
