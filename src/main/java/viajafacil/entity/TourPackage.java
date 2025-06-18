package viajafacil.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="tour_packages")
public class TourPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false)
	private int passangers;

    @Column(nullable = false)
	private String destination;

    @Column(nullable = false)
	private String departure_place;
    
    @Column(nullable = false)
	private String start_date;

    @Column(nullable = false)
	private String end_date;

    @Column(nullable = false)
	private String transport;

    @Column(nullable = false)
	private String description;

    @Column(nullable = false)
	private BigDecimal price;
    
    @OneToMany(mappedBy = "tourPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;
}
