package kz.zhelezyaka.spring6reactiveexperiments.controllers;

import kz.zhelezyaka.spring6reactiveexperiments.model.CustomerDTO;
import kz.zhelezyaka.spring6reactiveexperiments.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v2/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @DeleteMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(customerDTO -> customerService.deleteCustomerById(customerDTO.getId()))
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> patchExistingCustomer(@PathVariable Integer customerId,
                                                     @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(customerId, customerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(updateDTO -> ResponseEntity.ok().build());
    }

    @PutMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> updateExistingCustomer(@PathVariable("customerId") Integer customerId,
                                                      @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(savedDTO -> ResponseEntity.noContent().build());
    }

    @PostMapping(CUSTOMER_PATH)
    ResponseEntity<Void> createNewCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        AtomicInteger atomicInteger = new AtomicInteger();
        customerService.saveNewCustomer(customerDTO).subscribe(savedDTO -> {
            atomicInteger.set(savedDTO.getId());
        });

        return ResponseEntity.created(UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/" + CUSTOMER_PATH
                                + "/" + atomicInteger.get())
                        .build().toUri())
                .build();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    Mono<CustomerDTO> getCustomerById(@PathVariable("customerId") Integer customerId) {
        return customerService.getCustomerById(customerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping(CUSTOMER_PATH)
    Flux<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }
}
