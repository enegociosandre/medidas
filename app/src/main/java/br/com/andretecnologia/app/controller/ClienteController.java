package br.com.andretecnologia.app.controller;

import br.com.andretecnologia.app.dto.ClienteDTO;
import br.com.andretecnologia.app.model.Cliente;
import br.com.andretecnologia.app.service.impl.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.andretecnologia.app.adapter.ClienteAdapter.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obterTodos(){
        return ResponseEntity.ok(fromList(service.findAll()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteDTO> salvar(@RequestBody @Valid ClienteDTO dto){
        return ResponseEntity.ok(fromEntity(service.save(entityFrom(dto))));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> acharPorId(@PathVariable Integer id ){
        return ResponseEntity.ok(fromEntity(service.findById(id)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletar( @PathVariable Integer id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Integer id,
                                                @RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(fromEntity(service.update(id, cliente)));
    }
}
