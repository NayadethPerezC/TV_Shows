package sust.tv_shows.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sust.tv_shows.models.Network;
import sust.tv_shows.models.NetworkRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class NetworkController {

    @Autowired
    NetworkRepository netRepo;
    
    @GetMapping("/networks")
  public ModelAndView createScreen() {
    ModelAndView vista = new ModelAndView("networks.html");
    List<Network> networks = netRepo.findAll();
    vista.addObject("networks", networks);
    return vista;
  }

  @PostMapping("/networks")
  public String create(@RequestParam String name) {
    // 1. Creo la nueva cadena
    Network n = new Network();
    n.setName(name);
    // 2. La guardo
    netRepo.save(n);
    // 3. Redirijo a GET /networks
    return "redirect:/networks";
  }
    
    
}
