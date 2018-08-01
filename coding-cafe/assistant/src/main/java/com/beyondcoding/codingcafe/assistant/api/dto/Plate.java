package com.beyondcoding.codingcafe.assistant.api.dto;

import com.beyondcoding.codingcafe.assistant.persistence.domain.Foodstuff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plate {

    private Long ticket;

    private Foodstuff foodstuff;

}
