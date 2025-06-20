package viajafacil.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import viajafacil.dto.CartItemDTO;
import viajafacil.entity.User;
import viajafacil.service.CartService;
import viajafacil.service.UserService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam Long idTourPackage, Authentication authentication) {
        if(authentication != null) {
            String email = authentication.getName();
            User user = userService.getByEmail(email);
            if(user != null) {
                cartService.addItemToCart(user.getId(), idTourPackage);
                return "redirect:/inicio?agregado=true";
            }
        }
        return "redirect:/inicio?error=no_autenticado";
    }
    
    @PostMapping("/carrito/item/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        cartService.deleteCartItemById(id);
        return "redirect:/carrito/mostrar?deleted";
    }
    
    @GetMapping("/carrito/mostrar")
    public String mostrarCarrito(Authentication authentication, Model model) {
        if(authentication != null) {
            String email = authentication.getName();
            User user = userService.getByEmail(email);
            if(user != null) {
                List<CartItemDTO> listCartItems = cartService.showCartByUserId(user.getId());

                BigDecimal total = listCartItems.stream()
                	    .map(CartItemDTO::getPrice)
                	    .reduce(BigDecimal.ZERO, BigDecimal::add)
                	    .setScale(2, RoundingMode.HALF_UP);

                model.addAttribute("cartItems", listCartItems);
                model.addAttribute("totalCompra", total); 

                return "carrito";
            }
        }
        return "redirect:/inicio";
    }


    @PostMapping("/carrito/limpiar")
    public String limpiarCarrito(Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            User user = userService.getByEmail(email);
            if (user != null) {
                cartService.clearCartByUserId(user.getId());
            }
        }
        return "redirect:/carrito/mostrar?clear";
    }
}

