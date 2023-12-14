package com.example.springboot.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data

public class Facility {
     @Id
     int facilityId;
}
