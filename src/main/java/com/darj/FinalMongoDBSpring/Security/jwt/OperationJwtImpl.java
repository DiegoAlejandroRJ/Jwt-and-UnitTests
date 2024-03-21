package com.darj.FinalMongoDBSpring.Security.jwt;

import com.darj.FinalMongoDBSpring.dto.TokenDto;
import com.darj.FinalMongoDBSpring.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import static com.darj.FinalMongoDBSpring.utils.Constants.CLAIMS_ROLES_KEY;

@Component
public class OperationJwtImpl implements OperationJwt{

    @Value("${jwt.secret}")
    String secret;

    private String generateToken(User user, Date expirationDate){
        return Jwts.builder()
                .setSubject(user.getId()).claim(CLAIMS_ROLES_KEY, user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public TokenDto generateTokenDto(User user){
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.MINUTE, 30);
        String token = generateToken(user, expirationDate.getTime());
        return new TokenDto(token, expirationDate.getTime());
    }
}
