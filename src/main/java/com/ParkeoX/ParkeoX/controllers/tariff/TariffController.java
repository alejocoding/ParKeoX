package com.ParkeoX.ParkeoX.controllers.tariff;


import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffResponseDTO;
import com.ParkeoX.ParkeoX.services.tariff.ITariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("basics/tariff")
@RequiredArgsConstructor

public class TariffController {

    private final ITariffService tariffService;

    @GetMapping
    public ResponseEntity<List<TariffResponseDTO>> getTariffs() {
        return ResponseEntity.ok().body(tariffService.findAll());
    }

    @PostMapping
    public ResponseEntity<TariffRequestDTO> createTariff(@RequestBody TariffRequestDTO tariffRequestDTO) {
        TariffRequestDTO create = tariffService.CreateTariff(tariffRequestDTO);
        return ResponseEntity.created(URI.create("basics/tariff/" + create.getId())).body(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TariffRequestDTO> updateTariff(@PathVariable Long id, @RequestBody TariffRequestDTO tariffRequestDTO) {
        return ResponseEntity.ok().body(tariffService.UpdateTariff(id, tariffRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TariffRequestDTO> deleteTariff(@PathVariable Long id) {
        tariffService.DeleteTariff(id);
        return ResponseEntity.noContent().build();
    }
}
