package sg.edu.nus.iss.paf28_pokemon.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf28_pokemon.model.Pokemon;
import sg.edu.nus.iss.paf28_pokemon.repository.PokemonRepository;

@Service
public class PokemonService {
    
    private PokemonRepository pokeRepo;

    public PokemonService(PokemonRepository pokeRepo) {
        this.pokeRepo = pokeRepo;
    }

    public List<String> findAllTypes() {
        
        List<Document> docs = pokeRepo.findAllTypes();
        List<String> types = new ArrayList<>();

        for (Document doc : docs) {
            types.add(doc.getString("_id"));
        }

        return types;
    }

    public List<Pokemon> findPokemonByType(String type) {

        List<Document> docs = pokeRepo.findPokemonByType(type);
        List<Pokemon> pokemonList = new ArrayList<>();

        for (Document doc : docs) {
            Pokemon pokemon = new Pokemon();
            pokemon.setId(doc.getString("id"));
            pokemon.setName(doc.getString("name"));
            pokemon.setImg(doc.getString("img"));
            pokemonList.add(pokemon);
        }

        return pokemonList;
    }
}
