package sust.tv_shows.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sust.tv_shows.models.Network;
import sust.tv_shows.models.NetworkRepository;
import sust.tv_shows.models.Show;
import sust.tv_shows.models.ShowRepository;

@Component
public class ShowsDao {

     @Autowired
  NetworkRepository netRepo;

  @Autowired
  ShowRepository showRepo;

  public void create(String title, String description, String release_date, Long network_id) {
    // 1. Recuperamos la network
    Network n = netRepo.findById(network_id).get();
    // 2. Creamos el nuevo show
    Show s = new Show();
    s.setTitle(title);
    s.setRelease_date(release_date);
    s.setNetwork(n);
    s.setDescription(description);
    // 3. Lo guardamos
    showRepo.save(s);
  }

  public List<Show> findAll() {
    List<Show> shows = showRepo.findAll();

    for (Show show : shows) {
      show.setNetworkName(show.getNetwork().getName());
      show.setNetwork(null);
    }
    return shows;
  }
}
