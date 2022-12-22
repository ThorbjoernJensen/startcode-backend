package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class OwnerDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;

    private Set<BoatDTO> boatDTOS = new LinkedHashSet<>();


    public OwnerDTO(Owner owner) {
        if (owner.getId() != null) {
            this.id = owner.getId();
        }
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
    }


    public static Set<OwnerDTO> makeDTOSet(List<Owner> owners) {
        Set<OwnerDTO> ownerDTOSet = new LinkedHashSet<>();
        for (Owner owner : owners) {
            ownerDTOSet.add(new OwnerDTO(owner));
        }
        return ownerDTOSet;
    }

    public static Set<OwnerDTO> makeDTOSet(Set<Owner> owners) {
        Set<OwnerDTO> ownerDTOSet = new LinkedHashSet<>();
        for (Owner owner : owners) {
            ownerDTOSet.add(new OwnerDTO(owner));
        }
        return ownerDTOSet;
    }

    public static class BoatDTO {
        private Long id;
        private String brand;
        private String model;
        private String name;
        private String image;

        public BoatDTO(Boat boat) {
            if (boat.getId() != null) {
                this.id = boat.getId();
            }
            this.brand = boat.getBrand();
            this.model = boat.getModel();
            this.name = boat.getName();
            this.image = boat.getImage();

        }

        public static Set<BoatDTO> makeDTOSet(List<Boat> boatList) {
            Set<BoatDTO> boatDTOSet = new LinkedHashSet<>();
            boatList.forEach(boat -> boatDTOSet.add(new BoatDTO(boat)));
            return boatDTOSet;
        }


    }
}
