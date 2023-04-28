package kz.zhelezyaka.spring6reactiveexperiments.services;

import kz.zhelezyaka.spring6reactiveexperiments.mappers.BeerMapper;
import kz.zhelezyaka.spring6reactiveexperiments.model.BeerDTO;
import kz.zhelezyaka.spring6reactiveexperiments.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
}
