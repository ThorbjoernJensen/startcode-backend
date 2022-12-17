package dtos;

import entities.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BoatDTO {
    private Long id;
    private String brand;
    private String model;
    private String name;
    private String image;

    public BoatDTO(Boat boat) {
//        this.id = id;
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
}
