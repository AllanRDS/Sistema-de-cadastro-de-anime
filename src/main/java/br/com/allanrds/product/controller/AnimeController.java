package br.com.allanrds.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.allanrds.product.AnimeService;
import br.com.allanrds.product.entity.Anime;



@RestController
@RequestMapping("/animes")

public class AnimeController {


    private final AnimeService animeService;


    public AnimeController(AnimeService animeService) {

        this.animeService = animeService;

    }


    @PostMapping

    public ResponseEntity<Anime> criarAnime(@RequestBody Anime anime) {

        Anime novoAnime = animeService.salvarAnime(anime);

        return new ResponseEntity<>(novoAnime, HttpStatus.CREATED);

    }


    @GetMapping

    public ResponseEntity<List<Anime>> listarAnimes() {

        List<Anime> animes = animeService.listarAnimes();

        return new ResponseEntity<>(animes, HttpStatus.OK);

    }


    @GetMapping("/{id}")

    public ResponseEntity<Anime> buscarPorId(@PathVariable Long id) {

        Anime anime = animeService.buscarAnime(id);

        return ResponseEntity.ok(anime);

    }


    @PutMapping("/{id}")

    public ResponseEntity<Anime> atualizarAnime(@PathVariable Long id, @RequestBody Anime anime) {

        return ResponseEntity.ok(animeService.atualizarAnime(id, anime));

    }


    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deletarAnime(@PathVariable Long id) {

        animeService.deletarAnime(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
