package com.security.app.model;

import java.util.Set;

public enum Role {
DEVELOPER(Set.of(Permission.READ)),
ADMIN(Set.of(Permission.READ)),
MANAGER(Set.of(Permission.READ)),
USER(Set.of(Permission.READ));

private final Set<Permission> permissions;

Role(Set<Permission> permissions){
    this.permissions = permissions;
}
public Set<Permission> getPermissions(){
    return permissions;
}

}
