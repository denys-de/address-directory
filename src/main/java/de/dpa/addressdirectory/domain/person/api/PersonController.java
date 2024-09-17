package de.dpa.addressdirectory.domain.person.api;

import de.dpa.addressdirectory.domain.apicommon.Page;
import de.dpa.addressdirectory.domain.person.Person;
import de.dpa.addressdirectory.domain.person.PersonFilterCriteria;
import de.dpa.addressdirectory.domain.person.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Denys Babich
 * 16.09.2024
 */
@RestController
@RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PostMapping("/delete")
    void deletePerson(@RequestBody String code) {
        personService.deletePerson(code);
    }

    @PostMapping("/filter")
    Page<Person> getPersons(@RequestBody PersonFilterCriteria personFilterCriteria) {
        return personService.getPersons(personFilterCriteria);
    }

}
