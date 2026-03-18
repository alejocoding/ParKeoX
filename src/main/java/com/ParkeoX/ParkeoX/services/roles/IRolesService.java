package com.ParkeoX.ParkeoX.services.roles;



import com.ParkeoX.ParkeoX.DTO.request.rolesDTO.RolesDTO;

import java.util.List;

public interface IRolesService  {

    List<RolesDTO> findAll();
    RolesDTO createRol(RolesDTO rolesDTO);
    RolesDTO updateRol(Long id,RolesDTO rolesDTO);
    void deleteRol(Long id);
}
