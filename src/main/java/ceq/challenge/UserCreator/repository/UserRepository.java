package ceq.challenge.UserCreator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ceq.challenge.UserCreator.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	  List<User> findByEmail(String email);
}
