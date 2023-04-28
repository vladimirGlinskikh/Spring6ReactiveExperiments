package kz.zhelezyaka.spring6reactiveexperiments.mappers;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Beer;
import kz.zhelezyaka.spring6reactiveexperiments.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDTOTOBeer(BeerDTO dto);

    BeerDTO beerTOBeerDTO(Beer beer);
}
