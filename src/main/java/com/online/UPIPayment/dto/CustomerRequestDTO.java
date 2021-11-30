package com.online.UPIPayment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {

    @NotEmpty(message = "firstName should not be empty")
    @Size(min = 3, max = 20, message = " firstName  3 to 20 charcter")
    private String firstName;

    @NotEmpty(message = "lastName should not be empty")
    @Size(min = 3, max = 20, message = " firstName  3 to 20 charcter")
    private String lastName;

    @NotNull(message = "Phone Number should not be empty")
    private Long phoneNumber;
}
