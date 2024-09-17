package de.dpa.addressdirectory.domain.person.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Denys Babich
 * 16.09.2024
 */
public interface PersonNameRepository extends JpaRepository<PersonName, Long> {

}
