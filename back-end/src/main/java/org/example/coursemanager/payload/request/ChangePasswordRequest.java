package org.example.coursemanager.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePasswordRequest {
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Current password is mandatory")
    private String oldPassword;
    @NotBlank(message = "New password is mandatory")
    private  String newPassword;
}
