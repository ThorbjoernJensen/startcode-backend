package dtos;

import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;


    public OwnerDTO(Owner owner) {
        if (owner.getId() != null){
            this.id = owner.getId();
        }
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
    }

    public static List<OwnerDTO> makeDTOList(List<Owner> owners) {
        List<OwnerDTO> ownerDTOList = new ArrayList<>();
        for (Owner owner : owners) {
            ownerDTOList.add(new OwnerDTO(owner));
        }
        return ownerDTOList;
    }
}
