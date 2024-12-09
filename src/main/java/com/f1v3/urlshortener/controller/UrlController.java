package com.f1v3.urlshortener.controller;

import com.f1v3.urlshortener.dto.ShortenRequest;
import com.f1v3.urlshortener.dto.ShortenResponse;
import com.f1v3.urlshortener.serivce.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author 정승조
 * @version 2024. 12. 09.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/api/v1/shorten")
    public ResponseEntity<ShortenResponse> shorten(@RequestBody ShortenRequest request) {

        log.info("request : {}", request.getOriginalUrl());
        ShortenResponse response = urlService.shortenUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }


}
