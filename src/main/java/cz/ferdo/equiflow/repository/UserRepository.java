package cz.ferdo.equiflow.repository;

import cz.ferdo.equiflow.entity.UserEntity;
import cz.ferdo.equiflow.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getAllByRole(Role role);
}
