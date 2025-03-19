package br.com.allanrds.product; // Altere o pacote para refletir o novo contexto

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.allanrds.product.entity.Anime;; // Altere a importação para a nova entidade Anime
import br.com.allanrds.product.repository.AnimeRepository;; // Altere a importação para o novo repositório AnimeRepository
import br.com.allanrds.product.AnimeService;;; // Altere a importação para o novo serviço AnimeService

public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService; // Altere o serviço para AnimeService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarAnime() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setNome("Naruto");
        anime.setGenero("Ação");
        anime.setDescricao("Um jovem ninja busca reconhecimento.");

        when(animeRepository.save(any(Anime.class))).thenReturn(anime);

        Anime resultado = animeService.salvarAnime(anime);

        assertNotNull(resultado);
        assertEquals("Naruto", resultado.getNome());
        assertEquals("Ação", resultado.getGenero());
        assertEquals("Um jovem ninja busca reconhecimento.", resultado.getDescricao());
    }

    @Test
    void deveListarAnimes() {
        List<Anime> animes = Arrays.asList(
                new Anime(1L, "Dragon Ball", "Ação", "Um jovem lutador busca se tornar o mais forte."),
                new Anime(2L, "One Piece", "Aventura", "Um jovem pirata busca o tesouro mais valioso.")
        );

        when(animeRepository.findAll()).thenReturn(animes);

        List<Anime> resultado = animeService.listarAnimes();

        assertEquals(2, resultado.size());
        assertEquals("Dragon Ball", resultado.get(0).getNome());
        assertEquals("One Piece", resultado.get(1).getNome());
    }

    @Test
    void deveBuscarAnimePorId() {
        Anime anime = new Anime();
        anime.setId(1L);
        anime.setNome("Attack on Titan");
        anime.setGenero("Ação");
        anime.setDescricao("Humanos lutam contra gigantes.");

        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        Anime resultado = animeService.buscarAnime(1L);

        assertNotNull(resultado);
        assertEquals("Attack on Titan", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoAnimeNaoEncontrado() {
        when(animeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            animeService.buscarAnime(1L);
        });

        assertEquals("Anime não encontrado", exception.getMessage());
    }

    @Test
    void deveAtualizarAnime() {
        Anime animeExistente = new Anime();
        animeExistente.setId(1L);
        animeExistente.setNome("Naruto");
        animeExistente.setGenero("Ação");
        animeExistente.setDescricao("Um jovem ninja busca reconhecimento.");

        Anime animeAtualizado = new Anime();
        animeAtualizado.setId(1L);
        animeAtualizado.setNome("Naruto Shippuden");
        animeAtualizado.setGenero("Ação");
        animeAtualizado.setDescricao("Continuação da jornada de Naruto.");

        when(animeRepository.findById(1L)).thenReturn(Optional.of(animeExistente));
        when(animeRepository.save(any(Anime.class))).thenReturn(animeAtualizado);

        Anime resultado = animeService.atualizarAnime(1L, animeAtualizado);

        assertEquals("Naruto Shippuden", resultado.getNome());
        assertEquals("Ação", resultado.getGenero());
        assertEquals("Continuação da jornada de Naruto.", resultado.getDescricao());
    }

 @Test
    void deveDeletarAnime() {
        when(animeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(animeRepository).deleteById(1L);

        assertDoesNotThrow(() -> animeService.deletarAnime(1L));

        verify(animeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoDeletarAnimeNaoExistente() {
        when(animeRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            animeService.deletarAnime(1L);
        });

        assertEquals("Anime não encontrado", exception.getMessage());
    }
}