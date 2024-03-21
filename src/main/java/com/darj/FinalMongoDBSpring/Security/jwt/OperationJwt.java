package com.darj.FinalMongoDBSpring.Security.jwt;

import com.darj.FinalMongoDBSpring.dto.TokenDto;
import com.darj.FinalMongoDBSpring.model.User;

public interface OperationJwt {
    TokenDto generateTokenDto(User user);
}
