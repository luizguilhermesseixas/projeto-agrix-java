package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropsNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(
      CropRepository cropRepository,
      FertilizerRepository fertilizerRepository
  ) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Crop getCropsById(int id) throws CropsNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropsNotFoundException::new);
  }

  public List<Crop> findCropsByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * associate crop to fertilizer.
   */
  public Crop associateFertilizerToCrop(int cropId, int fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropsNotFoundException::new);

    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);

    fertilizer.getCrops().add(crop);
    crop.getFertilizers().add(fertilizer);

    fertilizerRepository.save(fertilizer);
    return cropRepository.save(crop);
  }
}
