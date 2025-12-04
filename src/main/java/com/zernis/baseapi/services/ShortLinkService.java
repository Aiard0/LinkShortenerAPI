package com.zernis.baseapi.services;

import com.zernis.baseapi.entities.ShortLink;
import com.zernis.baseapi.logics.RandomURL;
import com.zernis.baseapi.repository.ShortLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortLinkService {

    // Dependências
    private final RandomURL randomURL = new RandomURL();
    private final ShortLinkRepository shortLinkRepository;

    public ShortLinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    // Metodos

    public List<ShortLink> getAllShortLinks() {
        return shortLinkRepository.findAll();
    }

    public String createShortLink(String originalUrl) {
        String shortCode = randomURL.generateURL();
        ShortLink link = ShortLink.builder()
                .originalUrl(originalUrl)
                .shortCode(
                        shortCode
                )
                .build();
        shortLinkRepository.saveAndFlush(link);
        return shortCode;
    }

    public ShortLink getByShortCode(String shortCode) {
        return shortLinkRepository.findByShortCode(shortCode);
    }

    public String getOriginalUrl(String shortCode) {
        ShortLink link = getByShortCode(shortCode);
        if (link != null) {
            return link.getOriginalUrl();
        }
        return null;
    }

    public void deleteByShortCode(String shortcode) {
        shortLinkRepository.deleteByShortCode(shortcode);
    }

}
