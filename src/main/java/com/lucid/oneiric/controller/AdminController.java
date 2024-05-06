package com.lucid.oneiric.controller;

import com.lucid.oneiric.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("admin/delete/user/{userLogin}")
    public ResponseEntity<String> deleteUserByLogin(@PathVariable String userLogin) {
        if (adminService.deleteUserByLogin(userLogin)) {
            return ResponseEntity.ok("Deleted user " + userLogin);
        }
        return ResponseEntity.status(500).body("Failed operation.");

    }

    @PutMapping("admin/update/user/{userLogin}/role/{roleId}/add")
    public ResponseEntity<String> grantRoleToUser(@PathVariable String userLogin,@PathVariable Integer roleId) {
        if (adminService.grantRoleToUser(userLogin, roleId)) {
            return ResponseEntity.ok("Added role with id " + roleId + " to user " + userLogin);
        }
        return ResponseEntity.status(500).body("Failed operation.");

    }

    @PutMapping("admin/update/user/{userLogin}/role/{roleId}/remove")
    public ResponseEntity<String> revokeRoleToUser(@PathVariable String userLogin,@PathVariable Integer roleId) {
        if (adminService.removeRoleFromUser(userLogin, roleId)) {
            return ResponseEntity.ok("Revoked role with id " + roleId + " to user " + userLogin);
        }
        return ResponseEntity.status(500).body("Failed operation.");

    }
}
