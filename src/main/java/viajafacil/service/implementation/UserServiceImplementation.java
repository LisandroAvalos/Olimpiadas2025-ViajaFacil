package viajafacil.service.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import viajafacil.dto.UserDto;
import viajafacil.entity.Role;
import viajafacil.entity.User;
import viajafacil.repository.RoleRepository;
import viajafacil.repository.UserRepository;
import viajafacil.service.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	PasswordEncoder passwordEncoder;
	RoleRepository roleRepository;
	UserRepository userRepository;
	
	public UserServiceImplementation (PasswordEncoder passwordEncoder,
									RoleRepository roleRepository,
									UserRepository userRepository) {
									
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void saveUser(UserDto userDto) {
	    User user = new User();
	    user.setName(userDto.getFirstName() + " " + userDto.getLastName());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

	    Role role = roleRepository.findByName("ROLE_USER");

	    user.setRoles(Arrays.asList(role));
	    userRepository.save(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
	
	private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }
}