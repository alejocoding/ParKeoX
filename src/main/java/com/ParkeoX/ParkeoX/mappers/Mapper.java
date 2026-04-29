package com.ParkeoX.ParkeoX.mappers;

import com.ParkeoX.ParkeoX.DTO.request.brandDTO.BrandDTO;
import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.companyDTO.CompanyResponseDTO;
import com.ParkeoX.ParkeoX.DTO.request.licenseTypeDTO.LicenseTypeDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.licensesDTO.LicensesResponseDTO;
import com.ParkeoX.ParkeoX.DTO.request.paymentsDTO.PaymentsDTO;
import com.ParkeoX.ParkeoX.DTO.request.paymentsMethodDTO.PaymentsMethodDTO;
import com.ParkeoX.ParkeoX.DTO.request.rolesDTO.RolesDTO;
import com.ParkeoX.ParkeoX.DTO.request.statusDTO.StatusDTO;
import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.tariffDTO.TariffResponseDTO;
import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.ticketsDTO.TicketsResponseDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.usersDTO.UserResponseDTO;
import com.ParkeoX.ParkeoX.DTO.request.vehicleTypeDTO.VehicleTypeDTO;
import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesRequestDTO;
import com.ParkeoX.ParkeoX.DTO.request.vehiclesDTO.VehiclesResponseDTO;
import com.ParkeoX.ParkeoX.models.*;

public class Mapper {


    public static BrandDTO toDTO(Brand b) {

        if(b == null) return null;
        return BrandDTO.builder()
                .id(b.getId())
                .Brand(b.getBrand())
                .build();
    }

    public static CompanyRequestDTO toRequestDTO(Company c) {
        if(c == null) return null;

        return CompanyRequestDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .nit(c.getNit())
                .address(c.getAddress())
                .status(c.getStatus().getId())
                .createdAt(c.getCreatedAt())
                .build();
    }

    public static CompanyResponseDTO toResponseDTO(Company c) {
        if(c == null) return null;

        return CompanyResponseDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .nit(c.getNit())
                .address(c.getAddress())
                .status(c.getStatus().getStatus())
                .createdAt(c.getCreatedAt())
                .build();
    }

    public static RolesDTO toDTO(Roles r) {

        if(r == null) return null;

        return RolesDTO.builder()
                .id(r.getId())
                .rol(r.getRol())
                .build();

    }

    public static StatusDTO toDTO(Status s) {

        if(s == null) return null;

        return StatusDTO.builder()
                .id(s.getId())
                .status(s.getStatus())
                .build();

    }

    public static PaymentsMethodDTO toDTO(PaymentMethod pa){
        if(pa == null) return null;

        return PaymentsMethodDTO.builder()
                .id(pa.getId())
                .name(pa.getName())
                .active(pa.getActive())
                .build();
    }

    public static PaymentsDTO toDTO(Payments p) {
        if(p == null) return null;

        return PaymentsDTO.builder()
                .id(p.getId())
                .ticket(p.getTicket().getId())
                .PaymentMethod(p.getPaymentMethod().getId())
                .amount(p.getAmount())
                .paymentDate(p.getPaymentDate())
                .build();
    }

    public static VehicleTypeDTO toDTO(VehicleType vt) {
        if(vt == null) return null;

        return VehicleTypeDTO.builder()
                .id(vt.getId())
                .name(vt.getName())
                .build();
    }



    public static VehiclesRequestDTO toRequestDTO(Vehicles vs) {
        if(vs == null) return null;

        return VehiclesRequestDTO.builder()
                .plateNo(vs.getPlateNo())
                .brand(vs.getBrand().getId())
                .color(vs.getColor())
                .model(vs.getModel())
                .vehicleType(vs.getVehicleType().getId())
                .createdAt(vs.getCreatedAt())
                .build();
    }

    public static VehiclesResponseDTO toResponseDTO(Vehicles vs){
        if(vs == null) return null;

        return VehiclesResponseDTO.builder()
                .plateNo(vs.getPlateNo())
                .brand(vs.getBrand().getBrand())
                .color(vs.getColor())
                .model(vs.getModel())
                .vehicleType(vs.getVehicleType().getName())
                .createdAt(vs.getCreatedAt())
                .build();

    }


    public static TariffRequestDTO toRequestDTO(Tariff t) {
        if(t == null) return null;

        return TariffRequestDTO.builder()
                .id(t.getId())
                .company(t.getCompany().getId())
                .vehicleType(t.getVehicleType().getId())
                .active(t.getActive())
                .createdAt(t.getCreatedAt())
                .price(t.getPrice())
                .build();
    }

    public static TariffResponseDTO toResponseDTO(Tariff t){
        if(t == null) return null;

        return TariffResponseDTO.builder()
                .id(t.getId())
                .company(t.getCompany().getName())
                .vehicleType(t.getVehicleType().getName())
                .active(t.getActive())
                .createdAt(t.getCreatedAt())
                .price(t.getPrice())
                .build();

    }

    public static TicketsRequestDTO toRequestDTO(Tickets t) {
        if(t == null) return null;

        return TicketsRequestDTO.builder()
                .id(t.getId())
                .vehicle(t.getVehicle().getPlateNo())
                .checkInAt(t.getCheckInAt())
                .checkOutAt(t.getCheckOutAt())
                .tariff(t.getTariff().getId())
                .status(t.getStatus().getId())
                .total(t.getTotal())
                .build();
    }


    public static TicketsResponseDTO toResponseDTO(Tickets t){
        if(t == null) return null;

        return TicketsResponseDTO.builder()
                .id(t.getId())
                .vehicle(t.getVehicle().getPlateNo())
                .checkInAt(t.getCheckInAt())
                .checkOutAt(t.getCheckOutAt())
                .tariff(t.getTariff().getPrice())
                .status(t.getStatus().getStatus())
                .total(t.getTotal())
                .build();
    }

    public static LicenseTypeDTO toDTO(LicenseType lt) {
        if(lt == null) return null;

        return LicenseTypeDTO.builder()
                .id(lt.getId())
                .name(lt.getName())
                .MonthDuration(lt.getMonthDuration())
                .createdAt(lt.getCreatedAt())
                .build();

    }

    public static LicensesResponseDTO toResponseDTO(Licenses l) {
        if(l == null) return null;

        return LicensesResponseDTO.builder()
                .idLicense(l.getIdLicense())
                .company(l.getCompany().getName())
                .licenseType(l.getLicenseType().getName())
                .price(l.getPrice())
                .beginAt(l.getBeginAt())
                .endAt(l.getEndAt())
                .status(l.getStatus().getStatus())
                .createdAt(l.getCreatedAt())
                .build();
    }

    public static LicensesRequestDTO toRequestDTO(Licenses l) {
        if(l == null) return null;
        return LicensesRequestDTO.builder()
                .company(l.getCompany().getId())
                .licenseType(l.getLicenseType().getId())
                .price(l.getPrice())
                .beginAt(l.getBeginAt())
                .endAt(l.getEndAt())
                .status(l.getStatus())
                .createdAt(l.getCreatedAt())
                .build();

    }

    public static UserRequestDTO toRequestDTO(Users u) {
        if(u == null) return null;

        return UserRequestDTO.builder()
                .id(u.getId())
                .cedula(u.getCedula())
                .name(u.getName())
                .email(u.getEmail())
                .tel(u.getTel())
                .password(u.getPassword())
                .role(u.getRole().getId())
                .status(u.getStatus().getId())
                .company(u.getCompany().getId())
                .createdAt(u.getCreatedAt())
                .build();
    }

    public static UserResponseDTO toResponseDTO(Users u) {
        if(u == null) return null;

        return UserResponseDTO.builder()
                .id(u.getId())
                .cedula(u.getCedula())
                .name(u.getName())
                .email(u.getEmail())
                .tel(u.getTel())
                .role(u.getRole().getRol())
                .status(u.getStatus().getStatus())
                .company(u.getCompany().getName())
                .createdAt(u.getCreatedAt())
                .build();

    }

}
