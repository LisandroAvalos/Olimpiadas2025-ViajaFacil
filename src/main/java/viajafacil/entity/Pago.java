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
@Table(name="pago")
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false)
	private String name;

    @Column(nullable = false)
	private String dni;
	
    @Column(nullable = false)
	private String cardNumber;
	
    @Column(nullable = false)
	private String expirationDate;
	
    @Column(nullable = false)
	private String codeSec;

    @Column(nullable = false)
    private BigDecimal totalPrice;
}
