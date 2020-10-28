package br.com.andretecnologia.app.adapter;

import br.com.andretecnologia.app.dto.PetDTO;
import br.com.andretecnologia.app.model.Pet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PetAdapter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Pet entityFrom(PetDTO dto){
        return Pet.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .dataCadastro(Objects.nonNull(dto.getDataCadastro()) ? LocalDate.parse(dto.getDataCadastro(), formatter) : null)
                .build();
    }

    public static PetDTO fromEntity(Pet pet){

        return PetDTO.builder()
                .id(pet.getId())
                .nome(pet.getNome())
                .dataCadastro(Objects.nonNull(pet.getDataCadastro()) ? pet.getDataCadastro().format(formatter) : null)
                .build();
    }

    public static List <PetDTO > fromList (List< Pet > pets) {
        List < PetDTO > petsDTO = new ArrayList<>();
        return pets
                .stream()
                    .map(p -> PetDTO.builder()
                        .id(p.getId())
                        .nome(p.getNome())
                        .dataCadastro(p.getDataCadastro().format(formatter))
                        .build())
                .collect(Collectors.toList());
    }
}
