package br.com.allanrds.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.allanrds.product.entity.Anime;
import br.com.allanrds.product.repository.AnimeRepository;
@Service

public class AnimeService {


    @Autowired

    private AnimeRepository animeRepository;


    public Anime salvarAnime(Anime anime) {

        return animeRepository.save(anime);

    }


    public List<Anime> listarAnimes() {

        return animeRepository.findAll();

    }


    public Anime buscarAnime(Long id) {

        return animeRepository.findById(id).orElse(null);

    }


    public Anime atualizarAnime(Long id, Anime anime) {

        anime.setId(id);

        return animeRepository.save(anime);

    }


    public void deletarAnime(Long id) {

        animeRepository.deleteById(id);

    }

}