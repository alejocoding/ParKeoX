package com.ParkeoX.ParkeoX.mappers;

import com.ParkeoX.ParkeoX.DTO.request.brandDTO.BrandDTO;
import com.ParkeoX.ParkeoX.models.Brand;

public class Mapper {


    public static BrandDTO toDTO(Brand b) {

        if(b == null) return null;
        return BrandDTO.builder()
                .id(b.getId())
                .Brand(b.getBrand())
                .build();

    }
}
