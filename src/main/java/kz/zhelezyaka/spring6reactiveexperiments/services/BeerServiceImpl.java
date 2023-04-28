package kz.zhelezyaka.spring6reactiveexperiments.services;

import kz.zhelezyaka.spring6reactiveexperiments.mappers.BeerMapper;
import kz.zhelezyaka.spring6reactiveexperiments.model.BeerDTO;
import kz.zhelezyaka.spring6reactiveexperiments.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerTOBeerDTO);
    }

    @Override
    public Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerTOBeerDTO);
    }
}
