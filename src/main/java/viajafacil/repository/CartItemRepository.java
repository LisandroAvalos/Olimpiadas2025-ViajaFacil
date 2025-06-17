package viajafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import viajafacil.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
