package kz.zhelezyaka.spring6reactiveexperiments.services;

import kz.zhelezyaka.spring6reactiveexperiments.mappers.CustomerMapper;
import kz.zhelezyaka.spring6reactiveexperiments.model.CustomerDTO;
import kz.zhelezyaka.spring6reactiveexperiments.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public Flux<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .map(customerMapper::customerTOCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::customerTOCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO) {
        return customerRepository.save(customerMapper.customerDTOTOCustomer(customerDTO))
                .map(customerMapper::customerTOCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(foundCustomer -> {
                    foundCustomer.setCustomerName(customerDTO.getCustomerName());
                    foundCustomer.setEmail(customerDTO.getEmail());
                    return foundCustomer;
                }).flatMap(customerRepository::save)
                .map(customerMapper::customerTOCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> patchCustomer(Integer customerId, CustomerDTO customerDTO) {
        return customerRepository.findById(customerId)
                .map(foundCustomer -> {
                    if (StringUtils.hasText(customerDTO.getCustomerName())) {
                        foundCustomer.setCustomerName(customerDTO.getCustomerName());
                    }
                    if (StringUtils.hasText(customerDTO.getEmail())) {
                        foundCustomer.setEmail(customerDTO.getEmail());
                    }
                    return foundCustomer;
                }).flatMap(customerRepository::save)
                .map(customerMapper::customerTOCustomerDTO);
    }

    @Override
    public Mono<Void> deleteCustomerById(Integer customerId) {
        return customerRepository.deleteById(customerId);
    }
}
