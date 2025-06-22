package viajafacil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.transaction.annotation.Transactional;
import viajafacil.entity.Role;
import viajafacil.entity.TourPackage;
import viajafacil.entity.User;
import viajafacil.repository.RoleRepository;
import viajafacil.repository.TourPackageRepository;
import viajafacil.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");

        if (userRepository.findByEmail("jefeventas@gmail.com") == null) {
            Role jefeVentasRole = roleRepository.findByName("ROLE_ADMIN");

            User jefe = new User();
            jefe.setEmail("jefeventas@gmail.com");
            jefe.setName("Jefe de Ventas");
            jefe.setPassword(passwordEncoder.encode("1234"));

            List<Role> roles = new ArrayList<>();
            roles.add(jefeVentasRole);
            jefe.setRoles(roles);

            userRepository.save(jefe);
        }

        // Crear paquetes si no existen
        if (tourPackageRepository.count() == 0) {
            TourPackage paquete1 = new TourPackage();
            paquete1.setPassangers(20);
            paquete1.setDestination("Cataratas del Iguazú");
            paquete1.setDeparture_place("Buenos Aires");
            paquete1.setStart_date("2025-07-10");
            paquete1.setEnd_date("2025-07-15");
            paquete1.setTransport("Colectivo");
            paquete1.setDescription("Excursión a las cataratas con todo incluido");
            paquete1.setPrice(new BigDecimal("50000"));

            TourPackage paquete2 = new TourPackage();
            paquete2.setPassangers(15);
            paquete2.setDestination("Mendoza");
            paquete2.setDeparture_place("Córdoba");
            paquete2.setStart_date("2025-08-01");
            paquete2.setEnd_date("2025-08-06");
            paquete2.setTransport("Avión");
            paquete2.setDescription("Tour por bodegas y montañas de Mendoza");
            paquete2.setPrice(new BigDecimal("60000"));

            TourPackage paquete3 = new TourPackage();
            paquete3.setPassangers(4);
            paquete3.setDestination("Brasil");
            paquete3.setDeparture_place("Córdoba");
            paquete3.setStart_date("2025-10-06");
            paquete3.setEnd_date("2025-08-06");
            paquete3.setTransport("Avión");
            paquete3.setDescription("Viaje a las playas de arena blanca");
            paquete3.setPrice(new BigDecimal("35000"));

            tourPackageRepository.save(paquete1);
            tourPackageRepository.save(paquete2);
            tourPackageRepository.save(paquete3);
        }
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName) == null) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}


