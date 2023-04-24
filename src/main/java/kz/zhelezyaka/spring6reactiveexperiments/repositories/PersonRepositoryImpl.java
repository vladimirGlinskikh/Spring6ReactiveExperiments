package kz.zhelezyaka.spring6reactiveexperiments.repositories;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {
    Person petr = Person.builder()
            .id(1)
            .firstName("Petr")
            .lastName("Pupkin")
            .build();
    Person laura = Person.builder()
            .id(2)
            .firstName("Laura")
            .lastName("Pupkina")
            .build();
    Person mark = Person.builder()
            .id(3)
            .firstName("Mark")
            .lastName("Tesla")
            .build();

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(petr);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(petr, laura, mark);
    }
}
