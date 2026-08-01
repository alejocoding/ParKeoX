package com.ParkeoX.ParkeoX.repository.licensesRepository;

import com.ParkeoX.ParkeoX.models.Licenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<Licenses,String> {

     Optional<Licenses>  findByIdLicense(String idLicense);

    boolean existsByIdLicense(String uniqueLicence);

    List<Licenses> findByCompanyNit(String nit);

    Optional<Licenses> findFirstByCompany_IdOrderByEndAtDesc(Long companyId);

    List<Licenses> findByEndAtBeforeAndStatus_Id(LocalDateTime endAt, Long statusId);

}
