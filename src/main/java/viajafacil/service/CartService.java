package viajafacil.service;

import java.util.List;

import viajafacil.dto.CartItemDTO;
import viajafacil.entity.Cart;


public interface CartService {
	
	void clearCartByUserId(Long userId);
	
	void addItemToCart(Long idUser, Long idTourPackage);
	
	void deleteCartItemById(Long idItem);
	
	Cart getCartByUserId(Long userId);
	
	List<CartItemDTO> showCartByUserId(Long idUser);

}
