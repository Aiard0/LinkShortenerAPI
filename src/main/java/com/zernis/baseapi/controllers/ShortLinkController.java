package com.zernis.baseapi.controllers;

import com.zernis.baseapi.entities.ShortLink;
import com.zernis.baseapi.services.ShortLinkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/")
public class ShortLinkController {

    // Dependências

    private final ShortLinkService shortLinkService;

    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    // Métodos

    // Redireciona para a URL original com base no código curto fornecido
    @GetMapping("/{shortCode}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = shortLinkService.getOriginalUrl(shortCode);
        if (originalUrl != null) {
            return new RedirectView("https://" + originalUrl);
        }
        return new RedirectView("/not-found");
    }

    // GET Lista todas as entidades de ShortLink (UUID, URL original, código curto)
    @GetMapping("/list")
    public List<ShortLink> getAllShortLinks() {
        return shortLinkService.getAllShortLinks();
    }

    // GET Obtém a URL original com base no código curto fornecido
    @GetMapping("/search/{shortCode}")
    public String getOriginalUrl(@PathVariable String shortCode) {
        return shortLinkService.getOriginalUrl(shortCode);
    }

    // POST Cria um novo link curto a partir de uma URL original
    @PostMapping("/create/{url}")
    public String createShortLink(@PathVariable String url) {
        return shortLinkService.createShortLink(url);
    }

    // DELETE Deleta um link com base no código curto fornecido
    @DeleteMapping("/delete/{shortCode}")
    public void deleteShortLink(@PathVariable String shortCode) {
        shortLinkService.deleteByShortCode(shortCode);
    }

}
