package br.com.allanrds.product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import br.com.allanrds.product.entity.Anime;
import br.com.allanrds.product.repository.AnimeRepository;

public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarAnime() {
        Anime anime = new Anime(1L, "Naruto", "Ação", "Um jovem ninja busca reconhecimento.");

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
        Anime anime = new Anime(1L, "Attack on Titan", "Ação", "Humanos lutam contra gigantes.");

        when(animeRepository.findById(1L)).thenReturn(Optional.of(anime));

        Anime resultado = animeService.buscarAnime(1L);

        assertNotNull(resultado);
        assertEquals("Attack on Titan", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoAnimeNaoEncontrado() {
        when(animeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> animeService.buscarAnime(1L));

        assertEquals("Anime não encontrado", exception.getMessage());
    }

    @Test
    void deveAtualizarAnime() {
        Anime animeExistente = new Anime(1L, "Naruto", "Ação", "Um jovem ninja busca reconhecimento.");
        Anime animeAtualizado = new Anime(1L, "Naruto Shippuden", "Ação", "Continuação da jornada de Naruto.");

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

        Exception exception = assertThrows(RuntimeException.class, () -> animeService.deletarAnime(1L));

        assertEquals("Anime não encontrado", exception.getMessage());
    }
}
