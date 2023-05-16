package uz.reactiveprogramming.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private String at;
    private String description;
    private Integer status;
    private User user;

    public AuthResponse(String description, Integer status) {
        this.description = description;
        this.status = status;
    }
}
