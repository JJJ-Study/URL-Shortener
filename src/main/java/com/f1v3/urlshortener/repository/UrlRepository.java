package com.f1v3.urlshortener.repository;

import com.f1v3.urlshortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * {class name}.
 *
 * @author 정승조
 * @version 2024. 12. 09.
 */
public interface UrlRepository extends JpaRepository<Url, Long> {

    boolean existsByOriginalUrl(String originalUrl);

    Optional<Url> findByOriginalUrl(String originalUrl);
}
