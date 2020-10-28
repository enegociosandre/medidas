package br.com.andretecnologia.app.adapter;

import br.com.andretecnologia.app.dto.ClienteDTO;
import br.com.andretecnologia.app.dto.PetDTO;
import br.com.andretecnologia.app.model.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClienteAdapter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static Cliente entityFrom(ClienteDTO dto){
        return Cliente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .celular(dto.getCelular())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .dataCadastro(Objects.nonNull(dto.getDataCadastro()) ? LocalDate.parse(dto.getDataCadastro(), formatter) : null)
                .build();
    }

    public static ClienteDTO fromEntity(Cliente cliente){
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .celular(cliente.getCelular())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .dataCadastro(Objects.nonNull(cliente.getDataCadastro()) ? cliente.getDataCadastro().format(formatter) : null)
                .build();
    }

    public static List <ClienteDTO > fromList (List< Cliente > clientes) {
        List < PetDTO > clientesDTO = new ArrayList<>();
        return clientes
                .stream()
                .map(c -> ClienteDTO.builder()
                        .id(c.getId())
                        .nome(c.getNome())
                        .celular(c.getCelular())
                        .email(c.getEmail())
                        .telefone(c.getTelefone())
                        .dataCadastro(c.getDataCadastro().format(formatter))
                        .build())
                .collect(Collectors.toList());
    }
}
