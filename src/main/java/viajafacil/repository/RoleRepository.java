package viajafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viajafacil.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}