package viajafacil.service;

import java.util.List;
import org.springframework.stereotype.Service;

import viajafacil.dto.UserDto;
import viajafacil.entity.User;

@Service
public interface UserService{
	
	void saveUser(UserDto userDto);
	List<UserDto> getAllUsers();
	User getByEmail(String email);
}