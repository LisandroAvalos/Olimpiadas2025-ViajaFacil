package viajafacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import viajafacil.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByUser(Long userId);
	
}
