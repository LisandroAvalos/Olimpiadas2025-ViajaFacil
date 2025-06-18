package viajafacil.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	public void createCartForUser(Long idUser) {
		Optional<User> userOp = userRepository.findById(idUser);
		if(userOp.isPresent()) {
			User user = userOp.get();
			Cart cart = new Cart();
			cart.setUser(user);
			cartRepository.save(cart);	
		}
	}
	
	@Override
	public void clearCartByUserId(Long idUser) {
		Optional<Cart> cartOp = cartRepository.findByUserId(idUser);		
		if(cartOp.isPresent()) {
			Cart cart = cartOp.get();
			cart.getListCartItems().clear();
			cartRepository.save(cart);
		}
	}
	
	@Override
	public void addItemToCart(Long idUser, Long idTourPackage) {
		Optional<Cart> cartOp = cartRepository.findByUserId(idUser);
		Cart cart;
		if(cartOp.isPresent()) {
			cart = cartOp.get();
		}
		else {
            Optional<User> userOp = userRepository.findById(idUser);
            if (userOp.isEmpty()) {
                throw new RuntimeException("Usuario no encontrado");
            }
            User user = userOp.get();
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
		}
		Optional<TourPackage> tourPackageOp = tourPackageRepository.findById(idTourPackage);
		
		if(tourPackageOp.isPresent()) {
			TourPackage tourPackage = tourPackageOp.get();
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setTourPackage(tourPackage);
			cartItemRepository.save(cartItem);
		}
	}
	
	@Override
	public void deleteCartItemById(Long idItem) {
	    cartItemRepository.deleteById(idItem);
	}

	@Override
	public List<CartItemDTO> showCartByUserId(Long idUser) {
	    Optional<Cart> cartOp = cartRepository.findByUserId(idUser);
	    Cart cart;
	    if(cartOp.isPresent()) {
	        cart = cartOp.get();
	    } else {
	        Optional<User> userOp = userRepository.findById(idUser);
	        if (userOp.isEmpty()) {
	            throw new RuntimeException("Usuario no encontrado");
	        }
	        User user = userOp.get();
	        cart = new Cart();
	        cart.setUser(user);
	        cartRepository.save(cart);
	    }
	    
	    List<CartItemDTO> listDTO = new ArrayList<>();
	    for(CartItem item : cart.getListCartItems()) {
	        TourPackage tp = item.getTourPackage();
	        if (tp != null) {
	            CartItemDTO dto = new CartItemDTO();
	            dto.setCartItemId(item.getId());
	            dto.setDestination(tp.getDestination());
	            dto.setPrice(tp.getPrice());
	            dto.setPassangers(tp.getPassangers());
	            listDTO.add(dto);
	        }
	    }
	    return listDTO;
	}

}