package org.example.coursemanager.payload.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    Long id;
//    @NotBlank(message = "day one is mandatory")
    String dayOne;

//    @NotBlank(message = "day two is mandatory")
    String dayTwo;

}
