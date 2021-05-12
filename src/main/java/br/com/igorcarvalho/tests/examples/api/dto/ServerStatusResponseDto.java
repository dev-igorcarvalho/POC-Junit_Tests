package br.com.igorcarvalho.tests.examples.api.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Created by : 01001001 01000011 at 12/05/2021
 */
@Data
@Builder
public class ServerStatusResponseDto {
    private String appName;
    private String status;
    private String profile;
}
