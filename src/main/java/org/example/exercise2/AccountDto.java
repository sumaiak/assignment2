package org.example.exercise2;

import lombok.Getter;
import lombok.Setter;
import org.example.exercise1.MovieDto;


@Getter
@Setter

public class AccountDto {
    String fullname;
    String City;
    int zipCode;
    String isActive;




    public AccountDto(String fullnamex, String city, int zipCode, String isActive) {
        this.fullname = fullname;
        this.City = city;
        this.zipCode = zipCode;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "fullname='" + fullname + '\'' +
                ", City='" + City + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}


