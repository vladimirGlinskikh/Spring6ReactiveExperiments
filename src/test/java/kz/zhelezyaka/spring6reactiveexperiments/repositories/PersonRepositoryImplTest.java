package kz.zhelezyaka.spring6reactiveexperiments.repositories;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
        personMono.map(Person::getFirstName)
                .subscribe(firstName -> {
                    System.out.println(firstName);
                });
    }

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();

        Person person = personFlux.blockFirst();

        System.out.println(person.toString());
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.map(Person::getFirstName)
                .subscribe(firstName -> System.out.println(firstName));
    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> {
            list.forEach(person -> System.out.println(person.getFirstName()));
        });
    }

    @Test
    void testFilterOnName() {
        personRepository.findAll()
                .filter(person -> person.getFirstName().equals("Petr"))
                .subscribe(person -> System.out.println(person.getFirstName()));
    }

    @Test
    void testGetById() {
        Mono<Person> personMono = personRepository
                .findAll()
                .filter(person -> person.getFirstName().equals("Laura"))
                .next();
        personMono.subscribe(person -> System.out.println(person.getId()));
    }
}