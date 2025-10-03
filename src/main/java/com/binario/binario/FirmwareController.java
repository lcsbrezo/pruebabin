package com.binario.binario;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FirmwareController {

    @GetMapping("/firmware/latest")
    public ResponseEntity<InputStreamResource> getFirmware() throws IOException {
        // Ubicación del firmware dentro de resources
        ClassPathResource firmwareFile = new ClassPathResource("firmware/firmware.bin");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=firmware.bin")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(firmwareFile.contentLength())
                .body(new InputStreamResource(firmwareFile.getInputStream()));
    }
}

