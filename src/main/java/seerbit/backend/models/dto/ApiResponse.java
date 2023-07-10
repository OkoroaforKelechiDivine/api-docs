package seerbit.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private boolean success;

    private Map<String, String> data;

    private Map<String, String> error;

}
