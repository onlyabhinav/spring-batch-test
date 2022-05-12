package com.onlyabhinav.spring.batch.repository;

import com.onlyabhinav.spring.batch.entity.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateRepository extends JpaRepository<Estimate,Integer> {
}
