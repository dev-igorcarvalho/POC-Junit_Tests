package br.com.igorcarvalho.tests.examples.api.controller;

import br.com.igorcarvalho.tests.examples.api.dto.ServerStatusResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
@RequestMapping(ServerStatusController.BASE_URL)
public final class ServerStatusController {

    public static final String BASE_URL = "/api/v1/status";

    @Value("${app.profile}")
    private String enviroment;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public ResponseEntity<ServerStatusResponseDto> serverStatus(UriComponentsBuilder uriBuilder) {
        ServerStatusResponseDto status =
                ServerStatusResponseDto.builder()
                        .appName(appName)
                        .status("Online")
                        .profile(enviroment)
                        .build();
        return ResponseEntity.ok().body(status);
    }
}
