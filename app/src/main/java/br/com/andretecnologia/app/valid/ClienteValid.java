package br.com.andretecnologia.app.valid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteValid {

    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String celular;
}
