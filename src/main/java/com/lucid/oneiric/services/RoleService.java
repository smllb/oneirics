package com.lucid.oneiric.services;

import com.lucid.oneiric.entities.RoleEntity;
import com.lucid.oneiric.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private  final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getRoleById(Integer id) {
        return roleRepository.getReferenceById(id);

    }
}
