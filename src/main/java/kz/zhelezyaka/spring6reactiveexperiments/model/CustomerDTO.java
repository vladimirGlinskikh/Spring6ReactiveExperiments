package kz.zhelezyaka.spring6reactiveexperiments.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

    private int id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String customerName;

    @Size(max = 255)
    private String email;
}
