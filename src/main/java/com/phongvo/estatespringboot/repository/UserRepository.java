package com.phongvo.estatespringboot.repository;

import com.phongvo.estatespringboot.dto.UserResponse;
import com.phongvo.estatespringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndEnable(String name, boolean status);
    List<User> findAllByEnable(boolean enable);
    List<User> findAllByEnableAndRoles_Code(boolean enable, String code);
    boolean existsByIdAndBuildings_Id(Long staffId, Long  BuildingDd);
}
