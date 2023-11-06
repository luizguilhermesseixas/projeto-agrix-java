package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
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
 * fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  private final CropService cropService;

  @Autowired
  public FertilizerController(
      FertilizerService fertilizerService,
      CropService cropService
  ) {
    this.fertilizerService = fertilizerService;
    this.cropService = cropService;
  }

  /**
   * create fertilizer.
   */

  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(
      @RequestBody FertilizerDto fertilizerDto
  ) {
    Fertilizer createdFertilizer = fertilizerService.createFertilizer(fertilizerDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FertilizerDto.fromEntity(createdFertilizer));
  }

  /**
   * get all fertilizers.
   */
  @GetMapping
  public ResponseEntity<List<FertilizerDto>> findAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
    return ResponseEntity.status(HttpStatus.OK)
        .body(fertilizers.stream().map(FertilizerDto::fromEntity).toList());
  }

  /**
   * get fertilizer by id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> findFertilizerById(@PathVariable int id) {
    Fertilizer fertilizer = fertilizerService.findFertilizerById(id);
    return ResponseEntity.status(HttpStatus.OK).body(FertilizerDto.fromEntity(fertilizer));
  }

}
