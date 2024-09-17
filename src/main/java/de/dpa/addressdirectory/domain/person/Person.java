package de.dpa.addressdirectory.domain.person;

import java.time.LocalDate;

/**
 * @author Denys Babich
 * 16.09.2024
 */
public record Person(String code, String firstName, String lastName, LocalDate birthDate, String gender) {
}
