package com.sell.tea.controllers;

import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.dtos.response.UserResponseDto;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.repositories.UserRepository;
import com.sell.tea.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public ListEntityResponse<UserResponseDto> findAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortType", required = false) String sortType
    ){
            return  userService.findAll(name, page, limit, sortBy, sortType);
    }

    @GetMapping("/test")
    public String role(){
        return "hello user";
    }
}
