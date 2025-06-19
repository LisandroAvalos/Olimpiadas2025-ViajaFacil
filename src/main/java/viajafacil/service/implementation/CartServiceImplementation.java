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
	    
	    List<CartItemDTO> listCartItemDTO = new ArrayList<>();
	    for(CartItem cartItem : cart.getListCartItems()) {
	        TourPackage tp = cartItem.getTourPackage();
	        if (tp != null) {
	            CartItemDTO cartItemDTO = new CartItemDTO();
	            cartItemDTO.setId(cartItem.getId());
	            cartItemDTO.setPassangers(tp.getPassangers());
	            cartItemDTO.setDestination(tp.getDestination());
	            cartItemDTO.setDeparture_place(tp.getDeparture_place());
	            cartItemDTO.setStart_date(tp.getStart_date());
	            cartItemDTO.setEnd_date(tp.getEnd_date());
	            cartItemDTO.setTransport(tp.getTransport());
	            cartItemDTO.setDescription(tp.getDescription());
	            cartItemDTO.setPrice(tp.getPrice());
	            listCartItemDTO.add(cartItemDTO);
	        }
	    }
	    return listCartItemDTO;
	}

	@Override
	public Cart getCartByUserId(Long userId) {
	    return cartRepository.findByUserId(userId)
	        .orElseThrow(() -> new RuntimeException("No se encontró el carrito del usuario con ID " + userId));
	}

}