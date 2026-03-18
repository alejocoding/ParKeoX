package com.ParkeoX.ParkeoX.controllers.roles;


import com.ParkeoX.ParkeoX.DTO.request.rolesDTO.RolesDTO;
import com.ParkeoX.ParkeoX.services.roles.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("basics/roles")
public class RolesController {

    @Autowired
    IRolesService rolesService;

    @GetMapping
    public ResponseEntity<List<RolesDTO>> getRoles(){
        return ResponseEntity.ok().body(rolesService.findAll());
    }

    @PostMapping
    public ResponseEntity<RolesDTO> createRole(@RequestBody RolesDTO rolesDTO){

       RolesDTO newRol =  rolesService.createRol(rolesDTO);

        return ResponseEntity.created(URI.create("/roles/"+newRol.getId())).body(newRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesDTO> updateRole(@PathVariable Long id, @RequestBody RolesDTO rolesDTO){

        return ResponseEntity.ok(rolesService.updateRol(id, rolesDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id){

        rolesService.deleteRol(id);

        return ResponseEntity.noContent().build();

    }
}
