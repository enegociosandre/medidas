package br.com.andretecnologia.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 *
 * A classe <code>br.com.andretecnologia.app.model</code> é um modelo que descreve a entidade Pet
 *
 * @author André
 * @version 0.1.0
 * @see ApiModelProperty;
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id" , position = 0, value = "1", name = "id", dataType = "int", hidden = true)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @ApiModelProperty(notes = "Nome do Pet" , position = 1, value = "1", name = "name", dataType = "String", example = "Bob", required = true)
    private String nome;

    @Column(name = "data_cadastro", updatable = false)
    @ApiModelProperty(notes = "Data Cadastro" , position = 1, value = "03/07/2020", name = "dataCadastro", dataType = "LocalDate", example = "03/07/2020", hidden = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }
}
