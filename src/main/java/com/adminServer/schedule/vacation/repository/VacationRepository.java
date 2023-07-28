package com.adminServer.schedule.vacation.repository;

import com.adminServer.schedule.Status;
import com.adminServer.schedule.vacation.model.Vacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    Page<Vacation> findVacationsByStatus(Pageable pageable, Status status);
}
