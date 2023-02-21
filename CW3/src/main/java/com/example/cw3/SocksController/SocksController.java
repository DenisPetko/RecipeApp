package com.example.cw3.SocksController;

import com.example.cw3.model.Color;
import com.example.cw3.model.Size;
import com.example.cw3.model.SocksBatch;
import com.example.cw3.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "Api для учета носков", description = "Приход/Уход")
public class  SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/")
    @Operation(summary = "Регистрирует приход товара на склад.")
    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")

    public ResponseEntity<SocksBatch> accept(@RequestBody SocksBatch socksBatch) {
        return ResponseEntity.ok(socksService.accept(socksBatch));
    }

    @PutMapping("/")
    @Operation(summary = "Регистрирует отпуск носков со склада.")
    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")

    public ResponseEntity<Integer> issuance(@RequestBody SocksBatch socksBatch) {
        return ResponseEntity.ok(socksService.issuance(socksBatch));
    }

    @GetMapping("/")
    @Operation(summary = "Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса")
    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")

    public ResponseEntity<Integer> getCount(@RequestParam Color color,
                                         @RequestParam Size size,
                                         @RequestParam int amountCottonMin,
                                         @RequestParam int amountCottonMax) {
        return ResponseEntity.ok(socksService.getCount(color, size, amountCottonMin, amountCottonMax));
    }

    @DeleteMapping("/")
    @Operation(summary = "Регистрирует списание испорченных (бракованных) носков")
    @ApiResponse(responseCode = "200", description = "Операция выполнена успешно")
    @ApiResponse(responseCode = "400", description = "параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "произошла ошибка, не зависящая от вызывающей стороны")

    public ResponseEntity<Integer> reject(@RequestBody SocksBatch socksBatch) {
        return ResponseEntity.ok(socksService.reject(socksBatch));
    }

}
