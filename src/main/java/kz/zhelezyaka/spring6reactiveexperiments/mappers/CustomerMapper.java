package kz.zhelezyaka.spring6reactiveexperiments.mappers;

import kz.zhelezyaka.spring6reactiveexperiments.domain.Customer;
import kz.zhelezyaka.spring6reactiveexperiments.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CustomerMapper {

    Customer customerDTOTOCustomer(CustomerDTO dto);

    CustomerDTO customerTOCustomerDTO(Customer customer);
}
