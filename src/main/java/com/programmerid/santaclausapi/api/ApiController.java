package com.programmerid.santaclausapi.api;

import com.programmerid.santaclausapi.application.LetterStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1")
@RestController
public class ApiController {

    private final LetterStore store;

    @PostMapping(value = "/letters", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<ResponseEntity<AcceptedConfirmation>> createLetter(
            @RequestBody LetterToSantaCandidate letterCandidate) {
        log.info("New letter to Santa is received {}", letterCandidate);
        return CompletableFuture.supplyAsync(() -> ResponseEntity.accepted()
                .body(store.createLetter(letterCandidate)));
    }

    @GetMapping(value = "/letters/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    CompletableFuture<ResponseEntity<LetterToSantaView>> readLetter(
            @PathVariable String uuid) {
        log.info("Query for a letter with uuid {}", uuid);
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(
                LetterToSantaView.fromLetter(store.findLetter(uuid))));
    }

    @ExceptionHandler
    public Map<String, String> constraintViolation(ConstraintViolationException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getCause());
    }
}
