package viajafacil.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "venta_item")
public class VentaItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(nullable = false)
    private Long tour_package_id;
	
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
    
    @ManyToOne
	@JoinColumn(name = "venta_id")
	private Venta venta;
}
