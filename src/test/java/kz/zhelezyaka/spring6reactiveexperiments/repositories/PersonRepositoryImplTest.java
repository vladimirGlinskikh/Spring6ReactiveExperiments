package kz.zhelezyaka.spring6reactiveexperiments.repositories;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class PersonRepositoryImplTest {
    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);
        Person person = personMono.block();
        System.out.println(person.toString());
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(1);
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = personRepository.getById(2);
        personMono.map(person -> person.getFirstName())
                .subscribe(firstName -> {
                    System.out.println(firstName);
                });
    }
}