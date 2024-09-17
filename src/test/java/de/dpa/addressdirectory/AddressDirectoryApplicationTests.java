package de.dpa.addressdirectory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import de.dpa.addressdirectory.domain.person.persistence.Gender;
import de.dpa.addressdirectory.domain.person.persistence.Person;
import de.dpa.addressdirectory.domain.person.persistence.PersonName;
import de.dpa.addressdirectory.domain.person.persistence.PersonRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AddressDirectoryApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void contextLoads() {
        assertNotNull(personRepository);
    }

    @Test
    @Transactional
    void addPerson() {
        var newPerson = new Person();
        newPerson.setGender(Gender.MALE);
        newPerson.setBirthDate(LocalDate.now().minusYears(31));
        var personName = new PersonName();
        personName.setFirstName("Denys");
        personName.setLastName("Babich");
        personName.setEffectiveDate(LocalDateTime.now().minusYears(20));
        personName.setPerson(newPerson);
        newPerson.setPersonNames(List.of(personName));
        personRepository.save(newPerson);
        var savedPerson = personRepository.getReferenceById(newPerson.getId());
        assertEquals("Denys", savedPerson.getPersonNames().getFirst().getFirstName());
    }

}
