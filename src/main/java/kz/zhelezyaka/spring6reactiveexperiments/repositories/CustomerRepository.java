package kz.zhelezyaka.spring6reactiveexperiments.repositories;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
