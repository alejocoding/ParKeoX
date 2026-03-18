package com.ParkeoX.ParkeoX.services.status;

import com.ParkeoX.ParkeoX.DTO.request.rolesDTO.RolesDTO;
import com.ParkeoX.ParkeoX.DTO.request.statusDTO.StatusDTO;
import com.ParkeoX.ParkeoX.models.Status;

import java.util.List;

public interface IStatusService {

    List<StatusDTO> findAll();
    StatusDTO createStatus(StatusDTO statusDTO);
    StatusDTO updateStatus(Long id, StatusDTO statusDTO);
    Void deleteStatus(Long id);
}
