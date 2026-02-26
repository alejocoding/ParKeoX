package com.ParkeoX.ParkeoX.DTO.request.brandDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class BrandDTO {

    private Long id;
    @NotEmpty(message = "Brand cannot be empty")
    private String Brand;
}
