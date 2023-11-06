package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm Service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropService cropService;

  @Autowired
  public FarmService(
      FarmRepository farmRepository,
      CropService cropService
  ) {
    this.farmRepository = farmRepository;
    this.cropService = cropService;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Farm getFarmById(int id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

  /**
   *.
   */
  public Crop createCrop(Crop crop, int farmId) throws FarmNotFoundException {
    Farm farm = getFarmById(farmId);
    crop.setFarm(farm);
    return cropService.createCrop(crop);
  }
}
