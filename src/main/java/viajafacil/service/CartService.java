package viajafacil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import viajafacil.dto.CartItemDTO;

@Service
public interface CartService {
	
	void saveCart( Long idUser);
	
	void deleteCartById(Long idUser);
	
	void addItemToCart(Long idUser, Long idTourPackage);
	
	void deleteCartItemById(Long id);
}
