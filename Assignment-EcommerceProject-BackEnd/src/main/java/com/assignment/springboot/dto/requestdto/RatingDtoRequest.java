package com.assignment.springboot.dto.requestdto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class RatingDtoRequest {
	@Min(value = 0)
	@Max(value = 5)
	private int voteStar;
	@NotNull(message = "Required CustomerId")
	private long customer;
	@NotNull(message = "Required productId")
	private long productId;
	private Date createdDate;
}
