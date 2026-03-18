package com.ParkeoX.ParkeoX.services.roles;

import com.ParkeoX.ParkeoX.DTO.request.rolesDTO.RolesDTO;
import com.ParkeoX.ParkeoX.exceptions.NotFoundException;
import com.ParkeoX.ParkeoX.mappers.Mapper;
import com.ParkeoX.ParkeoX.models.Roles;
import com.ParkeoX.ParkeoX.repository.rolesRepository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesService implements IRolesService {

    private final RolesRepository repo;

    @Override
    public List<RolesDTO> findAll() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public RolesDTO createRol(RolesDTO rolesDTO) {

        Roles created = Roles.builder()
                .rol(rolesDTO.getRol())
                .build();

        return  Mapper.toDTO(repo.save(created));
    }

    @Override
    public RolesDTO updateRol(Long id, RolesDTO rolesDTO) {

        //if exist

        Roles rol = repo.findById(id).orElseThrow(()-> new NotFoundException("Roles not found"));
        rol.setRol(rolesDTO.getRol());

        return  Mapper.toDTO(repo.save(rol));

    }

    @Override
    public void deleteRol(Long id) {

        Roles rol = repo.findById(id).orElseThrow(()-> new NotFoundException("Roles not found to delete"));

        repo.deleteById(id);

    }
}
