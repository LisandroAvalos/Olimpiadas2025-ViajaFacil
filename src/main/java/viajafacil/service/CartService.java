package viajafacil.service;

import java.util.List;

import viajafacil.dto.CartItemDTO;


public interface CartService {
	
	void createCartForUser(Long idUser);
	
	void clearCartByUserId(Long userId);
	
	void addItemToCart(Long idUser, Long idTourPackage);
	
	void deleteCartItemById(Long idItem);
	
	List<CartItemDTO> showCartByUserId(Long idUser);

}
