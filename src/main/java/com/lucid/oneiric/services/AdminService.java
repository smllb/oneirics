package com.lucid.oneiric.services;

import com.lucid.oneiric.entities.RoleEntity;
import com.lucid.oneiric.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UsersService usersService;
    private final DreamService dreamService;

    private final OneiricsService oneiricsService;

    private final RoleService roleService;


    public AdminService(UsersService usersService, DreamService dreamService, OneiricsService oneiricsService, RoleService roleService) {
        this.usersService = usersService;
        this.dreamService = dreamService;
        this.oneiricsService = oneiricsService;
        this.roleService = roleService;
    }

    @Transactional
    public Boolean grantRoleToUser(String login, Integer roleId) {
        RoleEntity role = roleService.getRoleById(roleId);
        UserEntity user = usersService.getUserByLogin(login);
        if (!user.getRoles().contains(role)) {
            user.addRole(role);
            return true;
        }
        throw new IllegalStateException("User already have that role.");

    }

    @Transactional
    public Boolean removeRoleFromUser(String login, Integer roleId) {
        RoleEntity role = roleService.getRoleById(roleId);
        UserEntity user = usersService.getUserByLogin(login);
        if (user.getRoles().contains(role)) {
            user.removeRole(role);
            return true;
        }
        throw new IllegalStateException("User doesn't have that role.");

    }

    public Boolean deleteUserByLogin(String login) {
       return usersService.removeUserByLogin(login);

    }

    public Boolean suspendUserByLogin(String login) {
        return usersService.setUserStatusSuspendedByLogin(login);

    }
    public Boolean activateUserByLogin(String login){
        return usersService.setUserStatusActiveByLogin(login);

    }
}
