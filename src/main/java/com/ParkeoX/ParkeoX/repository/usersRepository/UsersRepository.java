package com.ParkeoX.ParkeoX.repository.usersRepository;

import com.ParkeoX.ParkeoX.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,String> {


    Optional<Users> findByEmail(String email);
    Optional<Users> findByCedula(String cedula);
}
