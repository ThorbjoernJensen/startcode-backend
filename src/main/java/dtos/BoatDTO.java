package dtos;

import entities.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BoatDTO {
    private Long id;
    private String brand;
    private String model;
    private String name;
    private String image;

    public BoatDTO(Boat boat) {

        if (boat.getId() != null){
            this.id = boat.getId();
        }
        this.brand = boat.getBrand();
        this.model = boat.getModel();
        this.name = boat.getName();
        this.image = boat.getImage();
    }

    public static List<BoatDTO> makeDTOList(Set<Boat> boatList){
        List<BoatDTO> boatDTOList = new ArrayList<>();
        boatList.forEach(boat -> boatDTOList.add(new BoatDTO(boat)));
        return boatDTOList;
    }

    public static List<BoatDTO> makeDTOList(List<Boat> boatList){
        List<BoatDTO> boatDTOList = new ArrayList<>();
        boatList.forEach(boat -> boatDTOList.add(new BoatDTO(boat)));
        return boatDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatDTO boatDTO = (BoatDTO) o;
        return Objects.equals(id, boatDTO.id) && Objects.equals(brand, boatDTO.brand) && Objects.equals(model, boatDTO.model) && Objects.equals(name, boatDTO.name) && Objects.equals(image, boatDTO.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, name, image);
    }
}
