package de.dpa.addressdirectory.domain.person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.dpa.addressdirectory.domain.apicommon.Page;
import de.dpa.addressdirectory.domain.person.persistence.Gender;
import de.dpa.addressdirectory.domain.person.persistence.PersonName;
import de.dpa.addressdirectory.domain.person.persistence.PersonRepository;
import de.dpa.addressdirectory.domain.utils.Utils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Denys Babich
 * 16.09.2024
 */
@Service
public class PersonService {

    private final PersonProperties personProperties;

    private final PersonRepository personRepository;

    public PersonService(PersonProperties personProperties, PersonRepository personRepository) {
        this.personProperties = personProperties;
        this.personRepository = personRepository;
    }

    @Transactional
    public void addPerson(Person person) {
        checkDuplicates(person);
        var newPerson = new de.dpa.addressdirectory.domain.person.persistence.Person();
        newPerson.setCode(Utils.generateRandomString(16));
        newPerson.setGender(Gender.valueOf(person.gender()));
        newPerson.setBirthDate(person.birthDate());
        var personName = new PersonName();
        personName.setFirstName(person.firstName());
        personName.setLastName(person.lastName());
        personName.setEffectiveDate(LocalDateTime.now());
        personName.setPerson(newPerson);
        newPerson.setPersonNames(List.of(personName));
        personRepository.save(newPerson);
    }

    private void checkDuplicates(Person person) {
        //TODO implement checks
    }

    @Transactional
    public void deletePerson(String code) {
        personRepository.findByCodeAndDeletedIsFalse(code).ifPresent(person -> {
            person.setDeleted(true);
            personRepository.save(person);
        });
    }

    @Transactional
    public Page<Person> getPersons(PersonFilterCriteria criteria) {
        var pageSize = personProperties.getPageSize();
        Specification<de.dpa.addressdirectory.domain.person.persistence.Person> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));
            Join<Person, PersonName> parameter = root.join("personNames");
            if (criteria.firstName() != null) {
                predicates.add(criteriaBuilder.like(parameter.get("firstName"), "%" + criteria.firstName() + "%"));
            }
            if (criteria.lastName() != null) {
                predicates.add(criteriaBuilder.like(parameter.get("lastName"), "%" + criteria.lastName() + "%"));
            }
            if (criteria.birthDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("birthDate"), criteria.birthDate()));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
        var pagable = PageRequest.of(criteria.page(), pageSize);
        var page = personRepository.findAll(specification, pagable);

        var resultPage = new Page<Person>();
        resultPage.setNumber(page.getNumber());
        resultPage.setTotalPages(page.getTotalPages());
        resultPage.setItems(page.get().map(person -> {
            var personNameOptional = person.getPersonNames().stream()
                    .max(Comparator.comparing(PersonName::getEffectiveDate));
            return personNameOptional.map(personName -> new Person(
                    person.getCode(),
                    personName.getFirstName(),
                    personName.getLastName(),
                    person.getBirthDate(),
                    person.getGender().name()
                    )).orElse(new Person(
                            person.getCode(),
                    null,
                    null,
                    person.getBirthDate(),
                    person.getGender().name()
            ));
        }).toList());
        return resultPage;
    }

}
