package com.cal.test.api.xite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CalculationResponseDTO {

    @JsonProperty("recordsList")
    List<CalculationResultResponseDTO> usersListResponseDtos = new ArrayList<>();

    private boolean status = true;

    public List<CalculationResultResponseDTO> getUsersListResponseDtos() {
        return usersListResponseDtos;
    }

    public void setUsersListResponseDtos(List<CalculationResultResponseDTO> usersListResponseDtos) {
        this.usersListResponseDtos = usersListResponseDtos;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
