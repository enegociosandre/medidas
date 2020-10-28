package br.com.andretecnologia.app.service.impl;

import br.com.andretecnologia.app.model.Pet;
import br.com.andretecnologia.app.repository.PetRepository;
import br.com.andretecnologia.app.service.CrudOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetService implements CrudOperations<Pet> {

    private final PetRepository repository;

    private static final String PET_NAO_ENCONTRADO = "Pet não encontrado";

    @Override
    public Pet save (Pet pet) {
        return repository.save(pet);
    }

    @Override
    public Pet findById(@PathVariable Integer id ){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, PET_NAO_ENCONTRADO) );
    }

    @Override
    public void delete( @PathVariable Integer id ){
        repository
                .findById(id)
                .map(pet -> {
                    repository.delete(pet);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, PET_NAO_ENCONTRADO) );
    }

    @Override
    public Pet update( Integer id, Pet pet) {
       return repository
                .findById(id)
                .map(p -> {
                    p.setNome(pet.getNome());
                    return repository.save(p);
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, PET_NAO_ENCONTRADO) );
    }

    @Override
    public List<Pet> findAll(){
        return repository.findAll();
    }

}
