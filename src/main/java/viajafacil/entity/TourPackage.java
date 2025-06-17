package viajafacil.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private int passengers;

    @Column(nullable = false)
	private String destination;

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
	
}
