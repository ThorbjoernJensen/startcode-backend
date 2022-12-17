package dtos;

import entities.Boat;
import entities.Harbour;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HarbourDTO {
    private Long id;

    private String name;

    private String address;

    private Integer capacity;
    private List<BoatDTO> boats;

    public HarbourDTO(Harbour harbour) {
        if (harbour.getId() != null){
            this.id = harbour.getId();
        }
        this.name = harbour.getName();
        this.address = harbour.getAddress();
        this.capacity = harbour.getCapacity();
        this.boats = BoatDTO.makeDTOList(harbour.getBoats());
    }

    public static List<HarbourDTO> makeDTOList(List<Harbour> harbours) {
        List<HarbourDTO> harbourDTOList = new ArrayList<>();
        harbours.forEach(harbour -> harbourDTOList.add(new HarbourDTO(harbour)));
        return harbourDTOList;
    }
}
