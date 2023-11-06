package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropsNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * get all crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();
    return ResponseEntity.status(HttpStatus.OK)
        .body(crops.stream().map(CropDto::fromEntity).toList());
  }

  /**
   * get crop by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropsById(@PathVariable int id) {
    Crop crop = cropService.getCropsById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(CropDto.fromEntity(crop));
  }

  /**
   * get crops between date.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> findCropsByHarvestDateBetween(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> crops = cropService.findCropsByHarvestDateBetween(start, end);
    return ResponseEntity.status(HttpStatus.OK)
        .body(crops.stream().map(CropDto::fromEntity).toList());
  }

  /**
   * associate crop to fertilizer.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateFertilizerToCrop(
      @PathVariable int cropId,
      @PathVariable int fertilizerId
  ) {
    cropService.associateFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * get fertilizers from crops.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersFromCrops(@PathVariable int cropId)
      throws CropsNotFoundException {
    Crop crop = cropService.getCropsById(cropId);
    List<Fertilizer> fertilizers = crop.getFertilizers();
    return ResponseEntity.status(HttpStatus.OK)
        .body(fertilizers.stream().map(FertilizerDto::fromEntity).toList());
  }
}
