package com.phongvo.estatespringboot.repository;

import com.phongvo.estatespringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByCode(String role);
}
