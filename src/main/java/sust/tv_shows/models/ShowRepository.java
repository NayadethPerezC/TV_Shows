package sust.tv_shows.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
  // save(Show s); -> Guarda una entidad
  // findAll(); -> List<Show> lista de shows
}
