package br.com.andretecnologia.app.dto;

import br.com.andretecnologia.app.model.Endereco;
import br.com.andretecnologia.app.valid.ClienteValid;
import br.com.andretecnologia.app.valid.PetValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoDTO {

    private Integer id;
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    private Endereco endereco;
    @Valid
    @NotNull(message = "{campo.pet.obrigatorio}")
    private PetValid pet;
    @Valid
    @NotNull(message = "{campo.cliente.obrigatorio}")
    private ClienteValid cliente;
    private String dataCadastro;
}
