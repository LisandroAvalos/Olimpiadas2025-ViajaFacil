package viajafacil.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import viajafacil.entity.Role;
import viajafacil.repository.RoleRepository;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName) == null) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}
