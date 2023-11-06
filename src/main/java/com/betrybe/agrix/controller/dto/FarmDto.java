package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * Farm record.
 */
public record FarmDto(Integer id, String name, Double size) {

  /**
   * Farm Dto.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * Farm Dto to entity.
   */
  public Farm toEntity() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);
    return farm;
  }

}
