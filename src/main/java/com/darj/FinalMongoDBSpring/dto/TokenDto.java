package com.darj.FinalMongoDBSpring.dto;

import java.util.Date;

public record TokenDto(
        String token,
        Date expirationDate) {

}
