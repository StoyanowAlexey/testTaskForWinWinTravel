package org.example.authapi.clients;


import org.example.authapi.DTOs.TransformRequest;
import org.example.authapi.DTOs.TransformResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "data-api",
        url = "http://data-api:8081"
)
public interface DataApiClient {

    @PostMapping("/transform")
    TransformResponse transform(
            @RequestHeader("X-Internal-Token") String token,
            @RequestBody TransformRequest request
    );
}