package com.ParkeoX.ParkeoX.services.status;

import com.ParkeoX.ParkeoX.DTO.request.statusDTO.StatusDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class StatusService implements IStatusService{

    @Autowired
    private StatusRepository repo;

    @Override
    public List<StatusDTO> findAll() {

        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public StatusDTO createStatus(StatusDTO statusDTO) {

        Status s = Status.builder()
                .id(statusDTO.getId())
                .status(statusDTO.getStatus())
                .build();

        return Mapper.toDTO(repo.save(s));
    }


    @Override
    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) {

            // IF EXIST
        Status status = repo.findById(id).orElseThrow( ()-> new NotFoundException("Status not found"));

        if(status.getId() == 1) throw new NotFoundException("CANNOT MODIFY STATUS");

        status.setStatus(statusDTO.getStatus());

        return  Mapper.toDTO(repo.save(status));
    }

    @Override
    public Void deleteStatus(Long id) {
        Status status = repo.findById(id).orElseThrow( ()-> new NotFoundException("Status not found"));
         repo.deleteById(id);
         return null;
    }
}
