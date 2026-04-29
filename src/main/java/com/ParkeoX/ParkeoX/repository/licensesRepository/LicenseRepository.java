package com.ParkeoX.ParkeoX.repository.licensesRepository;

import com.ParkeoX.ParkeoX.models.Licenses;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<Licenses,String> {

     Optional<Licenses>  findByIdLicense(String idLicense);

    boolean existsByIdLicense(String uniqueLicence);

}
