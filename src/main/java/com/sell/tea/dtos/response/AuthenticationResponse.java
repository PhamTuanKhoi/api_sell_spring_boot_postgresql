package com.sell.tea.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    private Long id;
    private String email;
    private String avatar;
}
