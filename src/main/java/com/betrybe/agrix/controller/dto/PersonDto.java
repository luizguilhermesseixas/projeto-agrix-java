package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * person record.
 */
public record PersonDto(Long id, String username, Role role) {
  /**
   * person dto.
   */

  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }

  /**
   * person to entity.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setRole(role);
    return person;
  }
}
