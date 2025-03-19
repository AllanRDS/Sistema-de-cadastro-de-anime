package br.com.allanrds.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.allanrds.product.entity.Anime;; // Altere a importação para a nova entidade Anime


public interface AnimeRepository extends JpaRepository<Anime, Long> {
}