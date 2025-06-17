package viajafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viajafacil.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

}