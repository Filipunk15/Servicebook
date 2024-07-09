package cz.filipunk.servicebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.filipunk.servicebook.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
