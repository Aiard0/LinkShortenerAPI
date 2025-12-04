package com.zernis.baseapi.repository;

import com.zernis.baseapi.entities.ShortLink;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink, UUID> {
    public ShortLink findByShortCode(String shortCode);

    @Transactional
    public void deleteByShortCode(String shortCode);
}
