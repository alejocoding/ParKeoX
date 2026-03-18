package com.ParkeoX.ParkeoX.controllers.status;

import com.ParkeoX.ParkeoX.DTO.request.rolesDTO.RolesDTO;
import com.ParkeoX.ParkeoX.DTO.request.statusDTO.StatusDTO;
import com.ParkeoX.ParkeoX.services.roles.IRolesService;
import com.ParkeoX.ParkeoX.services.status.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("basics/status")

public class StatusController {


    @Autowired
    IStatusService statusService;

    @GetMapping
    public ResponseEntity<List<StatusDTO>> getStatus(){
        return ResponseEntity.ok().body(statusService.findAll());
    }

    @PostMapping
    public ResponseEntity<StatusDTO> createStatus(@RequestBody StatusDTO rolesDTO){

        StatusDTO newStatus =  statusService.createStatus(rolesDTO);

        return ResponseEntity.created(URI.create("/status/"+newStatus.getId())).body(newStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDTO> updateStatus(@PathVariable Long id, @RequestBody StatusDTO statusDTO){

        return ResponseEntity.ok(statusService.updateStatus(id, statusDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id){

        statusService.deleteStatus(id);

        return ResponseEntity.noContent().build();

    }
}
