package com.example.delivery.feign;

import com.example.delivery.dto.ErrorLogRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "logsClient", url = "http://localhost:9000")
public interface LogsClient {
    @PostMapping("/log")
    void logError(@RequestBody ErrorLogRequest errorLog);
}
