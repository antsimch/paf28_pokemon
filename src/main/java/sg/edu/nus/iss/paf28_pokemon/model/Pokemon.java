package sg.edu.nus.iss.paf28_pokemon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    
    private String id;

    private String name;

    private String img;
}
