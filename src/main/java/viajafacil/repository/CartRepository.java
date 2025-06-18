package viajafacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import viajafacil.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Optional<Cart> findByUserId(Long userId);

	
}
