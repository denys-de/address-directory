package de.dpa.addressdirectory.domain.person.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Denys Babich
 * 16.09.2024
 */
//@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Optional<Person> findByCodeAndDeletedIsFalse(String code);

}
