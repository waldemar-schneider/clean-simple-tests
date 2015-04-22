package de.flaviait.user.domain;

import de.flaviait.user.entity.RoleEntity;

public class Role {

    private RoleEntity entity;

    public Role(RoleEntity entity) {
        this.entity = entity;
    }

    public Role(String roleName) {
        entity = new RoleEntity();
        entity.setName(roleName);
    }

    public String getName() {
        return entity.getName();
    }

    public void setName(String name) {
        this.entity.setName(name);
    }

    @Override
    public String toString() {
        return entity.getName();
    }
}
