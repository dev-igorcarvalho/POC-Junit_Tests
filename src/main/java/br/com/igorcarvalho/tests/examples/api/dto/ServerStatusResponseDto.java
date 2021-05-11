package br.com.igorcarvalho.tests.examples.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerStatusResponseDto {
    private String appName;
    private String status;
    private String profile;
}
