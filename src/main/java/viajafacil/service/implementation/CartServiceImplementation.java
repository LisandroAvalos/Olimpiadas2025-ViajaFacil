package viajafacil.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import viajafacil.dto.CartItemDTO;
import viajafacil.entity.Cart;
import viajafacil.entity.CartItem;
import viajafacil.entity.TourPackage;
import viajafacil.entity.User;
import viajafacil.repository.CartItemRepository;
import viajafacil.repository.CartRepository;
import viajafacil.repository.UserRepository;
import viajafacil.repository.TourPackageRepository;
import viajafacil.service.CartService;

@Service
public class CartServiceImplementation implements CartService{

	CartRepository cartRepository;
	CartItemRepository cartItemRepository;
	UserRepository userRepository;
	TourPackageRepository tourPackageRepository;
	
	public CartServiceImplementation(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, TourPackageRepository tourPackageRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
		this.tourPackageRepository = tourPackageRepository;
	}
	
	@Override
	public void saveCart(Long idUser) {
		Cart cart = cartRepository.findByUser(idUser);
		
		cartRepository.save(cart);	
	}
	
	@Override
	public void deleteCartById(Long idUser) {
		Cart cart = cartRepository.findByUser(idUser);		
		cartRepository.delete(cart);
		
	}
	
	@Override
	public void addItemToCart(Long idUser, Long idTourPackage) {
		Cart cart  = cartRepository.findByUser(idUser); 
		//TourPackage tourPackage = tourPackageRepository.findById(idTourPackage);
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		//cartItem.setDeparture_place();
		
		
		
	}
	
	@Override
	public void deleteCartItemById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
