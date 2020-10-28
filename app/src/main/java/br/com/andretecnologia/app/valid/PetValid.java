package br.com.andretecnologia.app.valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetValid {

    @NotNull(message = "{campo.pet.obrigatorio}")
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

}
