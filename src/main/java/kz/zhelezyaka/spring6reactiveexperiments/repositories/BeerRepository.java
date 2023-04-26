package kz.zhelezyaka.spring6reactiveexperiments.repositories;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
