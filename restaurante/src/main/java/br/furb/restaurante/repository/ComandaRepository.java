package br.furb.restaurante.repository;

import br.furb.restaurante.entity.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda, Long> {
}
