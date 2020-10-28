package br.com.andretecnologia.app.controller;

import br.com.andretecnologia.app.dto.AtendimentoDTO;
import br.com.andretecnologia.app.model.Atendimento;
import br.com.andretecnologia.app.model.Cliente;
import br.com.andretecnologia.app.model.Pet;
import br.com.andretecnologia.app.repository.AtendimenotRepository;
import br.com.andretecnologia.app.repository.ClienteRepository;
import br.com.andretecnologia.app.repository.EnderecoRepository;
import br.com.andretecnologia.app.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimenotRepository atendimenotRepository;

    private final EnderecoRepository enderecoRepository;

    private final ClienteRepository clienteRepository;

    private final PetRepository petRepository;

    private static final String REGISTRO_NAO_ENCONTRADO = "Registro não encontrado";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @GetMapping
    public List<Atendimento> obterTodos(){
        return atendimenotRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Atendimento salvar(@RequestBody @Valid AtendimentoDTO dto){

        Integer clienteId = dto.getCliente().getId();
        Integer petId = dto.getPet().getId();

        Cliente cliente = findClienteById(clienteId);

        Pet pet = findPetById(petId);

        return atendimenotRepository.save( Atendimento.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .endereco(dto.getEndereco())
                .cliente(cliente)
                .pet(pet)
                .dataCadastro(Objects.nonNull(dto.getDataCadastro()) ? LocalDate.parse(dto.getDataCadastro(), formatter) : null)
                .build());
    }

    @GetMapping("{id}")
    public Atendimento acharPorId(@PathVariable Integer id ){
        return atendimenotRepository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, REGISTRO_NAO_ENCONTRADO) );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Integer id ){
        atendimenotRepository
            .findById(id)
            .map(atendimento -> {
                atendimenotRepository.delete(atendimento);
                return Void.TYPE;
            })
            .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, REGISTRO_NAO_ENCONTRADO) );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Atendimento atualizar(@PathVariable Integer id,
                                 @RequestBody @Valid Atendimento atendimentoAtualizado) {

        Integer clienteId = atendimentoAtualizado.getCliente().getId();
        Integer petId = atendimentoAtualizado.getPet().getId();

        Cliente cliente = findClienteById(clienteId);
        Pet pet = findPetById(petId);

       Atendimento atendimento = atendimenotRepository
                .findById(id)
                .map( i -> {
                    i.setDescricao(atendimentoAtualizado.getDescricao());
                    i.setPet(pet);
                    i.setCliente(cliente);
                    return atendimenotRepository.save(i);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, REGISTRO_NAO_ENCONTRADO) );

        enderecoRepository
                .findById(atendimentoAtualizado.getEndereco().getId())
                .map( e -> {
                    e.setRua(atendimentoAtualizado.getEndereco().getRua());
                    e.setNumero(atendimentoAtualizado.getEndereco().getNumero());
                    e.setBairro(atendimentoAtualizado.getEndereco().getBairro());
                    e.setCidade(atendimentoAtualizado.getEndereco().getCidade());
                    e.setCep(atendimentoAtualizado.getEndereco().getCep());
                    return enderecoRepository.save(e);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, REGISTRO_NAO_ENCONTRADO) );

        return atendimento;
    }

    private Cliente findClienteById(Integer clienteId ) {
        return clienteRepository
                .findById(clienteId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, REGISTRO_NAO_ENCONTRADO));
    }

    private Pet findPetById(Integer petId) {
        return petRepository
                .findById(petId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, REGISTRO_NAO_ENCONTRADO));
    }
}
