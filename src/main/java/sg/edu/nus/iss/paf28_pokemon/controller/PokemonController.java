package sg.edu.nus.iss.paf28_pokemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.paf28_pokemon.service.PokemonService;

@Controller
@RequestMapping
public class PokemonController {
    
    private PokemonService pokeService;

    public PokemonController(PokemonService pokeService) {
        this.pokeService = pokeService;
    }

    @GetMapping(path = "/types")
    public ModelAndView getAllTypes() {
        
        ModelAndView mav = new ModelAndView();

        mav.setViewName("types");
        mav.addObject("types", pokeService.findAllTypes());

        return mav;
    }

    @GetMapping(path = "/type/{type}")
    public ModelAndView getPokemonByType(@PathVariable String type) {

        ModelAndView mav = new ModelAndView();

        mav.setViewName("pokemonByType");
        mav.addObject("pokemonList", pokeService.findPokemonByType(type));

        return mav;
    }

}
