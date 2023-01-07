package dtos;

import entities.Boat;
import entities.Harbour;

import java.util.*;

public class BoatDTO {
    private Long id;
    private String brand;
    private String model;
    private String name;
    private String image;
    private HarbourDTO harbourDTO;
    private Set<OwnerDTO> ownerDTOs;

    public BoatDTO(Boat boat) {
        if (boat.getId() != null) {
            this.id = boat.getId();
        }
        this.brand = boat.getBrand();
        this.model = boat.getModel();
        this.name = boat.getName();
        this.image = boat.getImage();
        this.harbourDTO = new HarbourDTO(boat.getHarbour());
        this.ownerDTOs = OwnerDTO.makeDTOSet(boat.getOwners());
    }

    public BoatDTO(String brand, String model, String name, String image) {
        this.brand = brand;
        this.model = model;
        this.name = name;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public HarbourDTO getHarbourDTO() {
        return harbourDTO;
    }

    public Set<OwnerDTO> getOwnerDTOs() {
        return ownerDTOs;
    }

    public static Set<BoatDTO> makeDTOSet(List<Boat> boatList) {
        Set<BoatDTO> boatDTOSet = new LinkedHashSet<>();
        boatList.forEach(boat -> boatDTOSet.add(new BoatDTO(boat)));
        return boatDTOSet;
    }

    public static class HarbourDTO {
        private Long id;

        private String name;

        private String address;

        private Integer capacity;

        public HarbourDTO(Harbour harbour) {
            if (harbour.getId() != null) {
                this.id = harbour.getId();
            }
            this.name = harbour.getName();
            this.address = harbour.getAddress();
            this.capacity = harbour.getCapacity();
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public Integer getCapacity() {
            return capacity;
        }
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
