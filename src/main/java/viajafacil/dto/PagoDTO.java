package viajafacil.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PagoDTO {
	
	@NotNull
	private Long id;
	
	@NotBlank
	private String name;

	@NotBlank
	private String dni;
	
	@NotBlank
	private String cardNumber;
	
	@NotBlank
	private String expirationDate;
	
	@NotBlank
	private String codeSec;

	@NotNull
    private BigDecimal totalPrice;
}
