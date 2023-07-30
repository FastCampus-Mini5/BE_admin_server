package com.adminServer.user.repository;

import com.adminServer.schedule.vacation.VacationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationInfoRepository extends JpaRepository<VacationInfo, Long> {
}
