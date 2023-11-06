package com.betrybe.agrix.controller;


import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm createdFarm = farmService.createFarm(farmDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(createdFarm));
  }

  /**
   * get all farms.
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();
    return ResponseEntity.status(HttpStatus.OK)
        .body(farms.stream().map(FarmDto::fromEntity).toList());
  }

  /**
   * get farm by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable int id) {
    Farm farm = farmService.getFarmById(id);
    return ResponseEntity.status(HttpStatus.OK)
        .body(FarmDto.fromEntity(farm));
  }

  /**
   * create crop.
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable("id") int id, @RequestBody CropDto cropDto
  ) throws FarmNotFoundException {
    Crop savedCrop = farmService.createCrop(cropDto.toEntity(), id);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CropDto.fromEntity(savedCrop));
  }

  /**
   * get crops from farms.
   */
  @GetMapping("/{id}/crops")
  public ResponseEntity<List<CropDto>> getCropsFromFarms(@PathVariable("id") int farmId)
      throws FarmNotFoundException {
    Farm farm = farmService.getFarmById(farmId);
    List<Crop> crops = farm.getCrops();
    return ResponseEntity.status(HttpStatus.OK)
        .body(crops.stream().map(CropDto::fromEntity).toList());
  }
}
