package kz.zhelezyaka.spring6reactiveexperiments.controllers;

import kz.zhelezyaka.spring6reactiveexperiments.model.BeerDTO;
import kz.zhelezyaka.spring6reactiveexperiments.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @PutMapping(BEER_PATH_ID)
    ResponseEntity<Void> updateExistingBeer(@PathVariable("beerId") Integer beerId,
                                            @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(beerId, beerDTO).subscribe();
        return ResponseEntity.ok().build();
    }

    @PostMapping(BEER_PATH)
    ResponseEntity<Void> createNewBeer(@RequestBody BeerDTO beerDTO) {
        AtomicInteger atomicInteger = new AtomicInteger();
        beerService.saveNewBeer(beerDTO).subscribe(savedDTO -> {
            atomicInteger.set(savedDTO.getId());
        });

        return ResponseEntity.created(UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/" + BEER_PATH
                                + "/" + atomicInteger.get())
                        .build().toUri())
                .build();
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable("beerId") Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }
}
