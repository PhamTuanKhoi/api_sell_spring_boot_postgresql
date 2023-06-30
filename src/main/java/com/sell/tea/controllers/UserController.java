package com.sell.tea.controllers;

import com.sell.tea.dtos.request.user.QueryUserDto;
import com.sell.tea.dtos.request.user.UpdateUserDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.dtos.response.UserResponseDto;
import com.sell.tea.repositories.UserRepository;
import com.sell.tea.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @GetMapping
    public ListEntityResponse<UserResponseDto> findAll(
            @ModelAttribute("query") QueryUserDto query
    ) {
        return userService.findAll(query.getName(), query.getPage(), query.getLimit(),
                query.getSortBy(), String.valueOf(query.getSortType()));
    }

    @PatchMapping("/{id}")
    public UserResponseDto update(@PathVariable("id") Long id, @RequestBody UpdateUserDto updateUserDto) {
        return userService.update(id, updateUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> delete(@PathVariable("id") Long id){
         return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }

}
