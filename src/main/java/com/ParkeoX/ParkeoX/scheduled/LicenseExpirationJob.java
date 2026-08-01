package com.ParkeoX.ParkeoX.scheduled;

import com.ParkeoX.ParkeoX.constants.StatusConstants;
import com.ParkeoX.ParkeoX.models.Licenses;
import com.ParkeoX.ParkeoX.models.Status;
import com.ParkeoX.ParkeoX.repository.licensesRepository.LicenseRepository;
import com.ParkeoX.ParkeoX.repository.statusRepository.StatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LicenseExpirationJob {

    private final LicenseRepository licenseRepository;
    private final StatusRepository statusRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void markExpiredLicenses() {
        List<Licenses> expired = licenseRepository.findByEndAtBeforeAndStatus_Id(LocalDateTime.now(), StatusConstants.ACTIVE_ID);

        if (expired.isEmpty()) {
            return;
        }

        Status inactive = statusRepository.findById(StatusConstants.INACTIVE_ID)
                .orElseThrow(() -> new IllegalStateException("Status INACTIVE (id=" + StatusConstants.INACTIVE_ID + ") no existe"));

        expired.forEach(license -> license.setStatus(inactive));
        licenseRepository.saveAll(expired);

        log.info("Se marcaron {} licencia(s) como vencidas", expired.size());
    }
}
