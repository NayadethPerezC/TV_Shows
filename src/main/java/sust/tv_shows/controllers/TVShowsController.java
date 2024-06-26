package sust.tv_shows.controllers;



import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sust.tv_shows.models.Network;
import sust.tv_shows.models.NetworkRepository;
import sust.tv_shows.models.Show;
import sust.tv_shows.models.ShowRepository;
import sust.tv_shows.services.ShowsDao;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TVShowsController {

  @Autowired
  ShowsDao showDao;

  @Autowired
  ShowRepository repo;

  @Autowired
  NetworkRepository netRepo;

  @GetMapping("/shows")
  public ModelAndView showsScreen() {
    ModelAndView vista = new ModelAndView("shows.html");
    List<Show> shows = showDao.findAll();
    vista.addObject("shows", shows);
    return vista;
  }

  @GetMapping("/shows/new")
  public ModelAndView createShowsScreen() {
    ModelAndView vista = new ModelAndView("new_show.html");
    List<Network> networks = netRepo.findAll();
    vista.addObject("networks", networks);
    return vista;
  }

  @PostMapping(value = "/shows/create")
  public String createShow(@RequestParam String title, @RequestParam String release_date, @RequestParam Long network_id,
      @RequestParam String description, RedirectAttributes redAt) {
    // System.out.println(network_id + "\n\n\n");
    showDao.create(title, description, release_date, network_id);
    redAt.addFlashAttribute("bien", "El show ha sido creado correctamente");
    return "redirect:/shows";
  }

  @GetMapping("/shows/{id}/destroy")
  public String deletebyId(@PathVariable Long id) {
    // 1. Borramos el Show con ID
    repo.deleteById(id);
    // 2. Redirigimos a la pantalla de SHOWS
    return "redirect:/shows";
  }

  @GetMapping("/shows/{id}/edit")
  public ModelAndView editShowScreen(@PathVariable Long id) {
    // 1. Creamos la vista
    ModelAndView view = new ModelAndView("edit.html");
    // 2. Recuperamos el show a partir de su ID
    Show s = repo.findById(id).get();
    s.setNetwork(null);
    // 3. Añadimos el show a la vista
    view.addObject("show", s);
    return view;
  }

  @PostMapping("/shows/{id}/edit")
  public String editShow(@PathVariable Long id, @RequestParam String title, @RequestParam String description,
      @RequestParam String network, @RequestParam String release_date, RedirectAttributes redAt) {
    // 1. Recupero el Show a partir de su ID
    Show s = repo.findById(id).get();
    // 2. Lo modifico
    s.setTitle(title);
    s.setDescription(description);
    s.setRelease_date(release_date);
    // s.setNetwork(network);
    // 3. Lo guardo
    repo.save(s);
    return "redirect:/shows";
    // 4. Agregamos mensaje de FEEDBACK
    // redAt.addFlashAttribute("bien", "Show actualizado correctamente");
    // 5. Redirijo a /shows
  }
    

}
