package com.adminServer.schedule.vacation.repository;

import com.adminServer.schedule.vacation.model.VacationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacationInfoRepository extends JpaRepository<VacationInfo, Long> {

    Optional<VacationInfo> findByUserId(Long userId);
}
