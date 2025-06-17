package viajafacil.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TourPackageDTO {

	private Long id;

	@Min(1)
	private int passengers;

	@NotBlank
	private String destination;

	@NotBlank
	private String start_date;

	@NotBlank
	private String end_date;

	@NotBlank
	private String transport;

	@NotBlank
	private String description;

	@NotNull
	private BigDecimal price;
}
