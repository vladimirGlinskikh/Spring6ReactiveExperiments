package kz.zhelezyaka.spring6reactiveexperiments.services;

import kz.zhelezyaka.spring6reactiveexperiments.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
    Flux<BeerDTO> listBeers();

    Mono<BeerDTO> getBeerById(Integer beerId);
}
