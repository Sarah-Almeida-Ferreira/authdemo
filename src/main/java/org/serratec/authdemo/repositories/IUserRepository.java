package org.serratec.authdemo.repositories;

import java.util.Optional;

import org.serratec.authdemo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	@Query(value = "FROM UserEntity u WHERE u.username = ?1")
	Optional<UserEntity> findByUsername(String username);
}
