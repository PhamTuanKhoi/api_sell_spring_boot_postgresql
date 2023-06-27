package com.sell.tea.services;

import com.sell.tea.dtos.request.user.UpdateUserDto;
import com.sell.tea.dtos.response.ListEntityResponse;
import com.sell.tea.dtos.response.UserResponseDto;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.exceptions.CatchException;
import com.sell.tea.exceptions.ResourceNotFoundException;
import com.sell.tea.map.UserEntityAndUserRequestDtoMapper;
import com.sell.tea.repositories.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserEntityAndUserRequestDtoMapper userEntityAndUserRequestDtoMapper;

    public Optional<UserEntity> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public Optional<UserEntity> findByName(String name) {
        return this.userRepository.findByName(name);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public ListEntityResponse<UserResponseDto> findAll(String name, Integer page, Integer limit, String sortBy, String sortType) {
//        filter query
        Specification<UserEntity> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if ((name != null) && !(name.isEmpty()))
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
//        paging sort
        PageRequest pageRequest = null;
        if (sortBy != null && sortType != null && page != null && limit != null) {
            Sort.Direction direction = sortType.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageRequest = PageRequest.of(page - 1, limit, direction, sortBy);
        }

        if (page != null && limit != null && sortBy == null && sortType == null)
            pageRequest = PageRequest.of(page - 1, limit);

        if (sortBy == null && sortType == null && page == null && limit == null)
            pageRequest = PageRequest.ofSize(userRepository.findAll().size());

        Page entityPage = userRepository.findAll(specification, pageRequest);
//        map data

        List<UserResponseDto> entities = new ArrayList<>();
        entityPage.getContent().forEach(item -> {
            UserResponseDto userResponseDto = modelMapper.map(item, UserResponseDto.class);
            entities.add(userResponseDto);
        });

        Long count = entityPage.getTotalElements();

        return new ListEntityResponse<UserResponseDto>(entities, count, limit, page);
    }

    public UserResponseDto update(Long id, UpdateUserDto updateUserDto) {
        UserEntity user = this.isEntityExists(id);
        userEntityAndUserRequestDtoMapper.map(updateUserDto, user);

        try {
            UserEntity saved = userRepository.save(user);
            log.info("updated a user by id#" + saved.getId());

            UserResponseDto userResponseDto = new UserResponseDto();
            modelMapper.map(saved, userResponseDto);

            return userResponseDto;
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }

    public UserResponseDto delete(Long id) {
        UserEntity user = this.isEntityExists(id);
        try {
            userRepository.deleteById(id);
            log.info("deleted a user by id#" + user.getId());
            UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
            return userResponseDto;
        } catch (Exception ex) {
            throw new CatchException(ex.getMessage());
        }
    }

    public UserEntity isEntityExists(Long id) {
        return this.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found by id#" + id));
    }
}
