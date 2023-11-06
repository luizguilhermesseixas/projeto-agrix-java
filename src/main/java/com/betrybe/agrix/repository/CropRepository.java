package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * crop repository.
 */
public interface CropRepository extends JpaRepository<Crop, Integer> {

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
