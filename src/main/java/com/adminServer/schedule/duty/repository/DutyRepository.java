package com.adminServer.schedule.duty.repository;

import com.adminServer.schedule.Status;
import com.adminServer.schedule.duty.model.Duty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DutyRepository extends JpaRepository<Duty, Long> {
    Page<Duty> findDutyByStatus(Pageable pageable, Status status);
}
