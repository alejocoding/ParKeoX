package com.ParkeoX.ParkeoX.repository.usersRepository;

import com.ParkeoX.ParkeoX.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {


    Optional<Users> findByEmail(String email);
    Optional<Users> findByCedula(String cedula);
    List<Users> findByCompanyNit(String nit);

    @Query("SELECT u FROM Users u WHERE u.company.nit = :nit AND u.role.rol <> :excludedRole")
    List<Users> findByCompanyNitExcludingRole(@Param("nit") String nit, @Param("excludedRole") String excludedRole);
}
