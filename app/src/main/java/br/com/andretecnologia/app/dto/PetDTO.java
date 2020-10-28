package br.com.andretecnologia.app.dto;

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
public class PetDTO {

    private Integer id;
    @NotNull(message = "{campo.nome.obrigatorio}")
    @Column(nullable = false, length = 150)
    private String nome;
    private String dataCadastro;

}
