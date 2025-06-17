package viajafacil.entity;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false)
    private BigDecimal total_price;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
	
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> listCartItems;

} 
    
    
    
    
