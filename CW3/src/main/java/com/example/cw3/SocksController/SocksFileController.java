package com.example.cw3.SocksController;

import com.example.cw3.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks/files")
@Tag(name = "Api для работы с файлом носков", description = "Импорт/Экспорт файла")
@RequiredArgsConstructor
public class SocksFileController {
    private final SocksService socksService;

    @GetMapping("/export")
    @Operation(summary = "Выгрузка json файла носков")
    public ResponseEntity<InputStreamResource> downloadSocksJson() {
        try {
            File socksFile = socksService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(socksFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(socksFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + socksFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/import")
    @Operation(summary = "Загрузка json файла носков")
    public ResponseEntity<Void> uploadSocksJson(@RequestParam MultipartFile file) {
        try {
            socksService.importFromFile(file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
