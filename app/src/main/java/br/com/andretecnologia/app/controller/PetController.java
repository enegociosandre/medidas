package br.com.andretecnologia.app.controller;

import br.com.andretecnologia.app.dto.PetDTO;
import br.com.andretecnologia.app.service.impl.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static br.com.andretecnologia.app.adapter.PetAdapter.*;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@Api(value= "Pets")
public class PetController {

    private final PetService service;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Listagem de Pets")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retornou a lista com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
    })
    public ResponseEntity<List<PetDTO>> getAll(){
        try {
            return ResponseEntity.ok(fromList(service.findAll()));
        } catch(Throwable exception) {
            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

            errorAsJson.put("verb", "GET");
            errorAsJson.put("exception", exception.getClass());
            errorAsJson.put("path", "/pets");
            errorAsJson.put("timestamp", Instant.now());
            errorAsJson.put("message", exception.getMessage());

            return new ResponseEntity(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Cadastrar Pet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criado com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado"),
            @ApiResponse(code = 400, message = "Um registro com este nome já existe")
    })
    public ResponseEntity<PetDTO> salvar(@RequestBody @Valid PetDTO dto){
        try {
            return ResponseEntity.ok(fromEntity(service.save(entityFrom(dto))));
        } catch(Throwable exception) {
            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

            errorAsJson.put("verb", "POST");
            errorAsJson.put("exception", exception.getClass());
            errorAsJson.put("path", "/pets");
            errorAsJson.put("timestamp", Instant.now());
            errorAsJson.put("message", exception.getMessage());

            return new ResponseEntity(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Visualizar Pet por id")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
            @ApiResponse(code = 404, message = "O recurso não foi encontrado")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PetDTO> getById(@PathVariable Integer id ){
        try {
            return ResponseEntity.ok(fromEntity(service.findById(id)));
        } catch(Throwable exception) {
            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

            errorAsJson.put("verb", "GET");
            errorAsJson.put("exception", exception.getClass());
            errorAsJson.put("path", "/pets" + id);
            errorAsJson.put("timestamp", Instant.now());
            errorAsJson.put("message", exception.getMessage());

            return new ResponseEntity(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletar Pet")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Executado sem conteúdo de retorno"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
            @ApiResponse(code = 400, message = "O recurso não foi encontrado")
    })
    public ResponseEntity<Void>  deletar( @PathVariable Integer id ){
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch(Throwable exception) {
            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

            errorAsJson.put("verb", "DELETE");
            errorAsJson.put("exception", exception.getClass());
            errorAsJson.put("path", "/pets" + id);
            errorAsJson.put("timestamp", Instant.now());
            errorAsJson.put("message", exception.getMessage());

            return new ResponseEntity(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Alterar Pet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Alterado com sucesso"),
            @ApiResponse(code = 401, message = "Você não está autorizado a ver esse recurso"),
            @ApiResponse(code = 403, message = "Acessar o recurso que você está tentando não é permitido"),
            @ApiResponse(code = 400, message = "O recurso não foi encontrado")
    })
    public ResponseEntity<PetDTO> atualizar( @PathVariable Integer id,
                           @RequestBody @Valid PetDTO dto) {
        try {
            return ResponseEntity.ok(fromEntity(service.update(id, entityFrom(dto))));
        } catch(Throwable exception) {
            java.util.Map<String, Serializable> errorAsJson = new java.util.HashMap<String, Serializable>();

            errorAsJson.put("verb", "PUT");
            errorAsJson.put("exception", exception.getClass());
            errorAsJson.put("path", "/pets" + id);
            errorAsJson.put("timestamp", Instant.now());
            errorAsJson.put("message", exception.getMessage());

            return new ResponseEntity(errorAsJson, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
