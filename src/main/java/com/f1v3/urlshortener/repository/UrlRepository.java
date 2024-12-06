package com.f1v3.urlshortener.repository;

import com.f1v3.urlshortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 정승조
 * @version 2024. 12. 06.
 */
public interface UrlRepository extends JpaRepository<Url, Long> {

    boolean existsByOriginalUrl(String originalUrl);

    Url findByOriginalUrl(String originalUrl);
}
