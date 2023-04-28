package kz.zhelezyaka.spring6reactiveexperiments.services;

import kz.zhelezyaka.spring6reactiveexperiments.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {
    Flux<BeerDTO> listBeers();
}
