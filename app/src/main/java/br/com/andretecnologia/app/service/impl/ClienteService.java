package br.com.andretecnologia.app.service.impl;

import br.com.andretecnologia.app.model.Cliente;
import br.com.andretecnologia.app.repository.ClienteRepository;
import br.com.andretecnologia.app.service.CrudOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService implements CrudOperations<Cliente> {

    private final ClienteRepository repository;

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";

    @Override
    public Cliente save (Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO) );
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Integer id){
        repository
                .findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO) );
    }

    @Override
    public Cliente update(Integer id, Cliente cliente) {
        return repository
                .findById(id)
                .map(c -> {
                    c.setNome(cliente.getNome());
                    c.setEmail(cliente.getEmail());
                    c.setTelefone(cliente.getTelefone());
                    c.setCelular(cliente.getCelular());
                    return repository.save(c);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CLIENTE_NAO_ENCONTRADO) );
    }
}
