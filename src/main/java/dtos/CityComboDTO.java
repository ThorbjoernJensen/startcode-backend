package dtos;

public class CityComboDTO {
    private final String pokemonId;
    private final String pokemonName;
    private final String pokemonImage;
    private final String randomFact;

    public CityComboDTO(PokemonDTO pokemonDTO, RandomFactDTO randomFactDTO) {
        this.pokemonId = pokemonDTO.getId();
        this.pokemonName = pokemonDTO.getName();
        this.pokemonImage = pokemonDTO.getImageURL();
        this.randomFact = randomFactDTO.getFact();
    }
}
