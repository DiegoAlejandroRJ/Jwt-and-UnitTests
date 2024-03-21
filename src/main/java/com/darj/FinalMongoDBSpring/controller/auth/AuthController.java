package com.darj.FinalMongoDBSpring.controller.auth;

import com.darj.FinalMongoDBSpring.Security.encrypt.PasswordEncryptionService;
import com.darj.FinalMongoDBSpring.Security.jwt.OperationJwt;
import com.darj.FinalMongoDBSpring.dto.LoginDto;
import com.darj.FinalMongoDBSpring.dto.TokenDto;
import com.darj.FinalMongoDBSpring.exceptions.InvalidCredentialsException;
import com.darj.FinalMongoDBSpring.exceptions.UserNotFoundException;
import com.darj.FinalMongoDBSpring.model.User;
import com.darj.FinalMongoDBSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncryptionService passwordEncryptionService;
    private final OperationJwt operationJwt;

    @Autowired
    public AuthController(UserService userService, PasswordEncryptionService passwordEncryptionService, OperationJwt operationJwt) {
        this.userService = userService;
        this.passwordEncryptionService = passwordEncryptionService;
        this.operationJwt = operationJwt;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto){
        User userFound = userService.findByEmail(loginDto.getEmail());
        if (userFound == null){
            throw new UserNotFoundException(loginDto.getEmail());
        }

        //UserResponseDto user = optionalUser.get();

        if (passwordEncryptionService.isPasswordMatch(loginDto.getPassword(), userFound.getPassword())){
            return new ResponseEntity<>(operationJwt.generateTokenDto(userFound), HttpStatus.OK);
        }else {
            throw new InvalidCredentialsException();
        }

    }
}
