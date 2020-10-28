package br.com.andretecnologia.app.controller;

import br.com.andretecnologia.app.dto.ClienteDTO;
import br.com.andretecnologia.app.dto.PetDTO;
import br.com.andretecnologia.app.model.Atendimento;
import br.com.andretecnologia.app.model.Cliente;
import br.com.andretecnologia.app.model.Endereco;
import br.com.andretecnologia.app.model.Pet;
import br.com.andretecnologia.app.repository.AtendimenotRepository;
import br.com.andretecnologia.app.repository.ClienteRepository;
import br.com.andretecnologia.app.repository.EnderecoRepository;
import br.com.andretecnologia.app.repository.PetRepository;
import br.com.andretecnologia.app.service.impl.ClienteService;
import br.com.andretecnologia.app.service.impl.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({PetController.class, ClienteController.class, AtendimentoController.class})
public class MockMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @MockBean
    private PetRepository petRepository;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private AtendimenotRepository atendimentoRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;

    private static final String ONE_PET = "src/test/resources/one-pet.json";
    private static final String ONE_CLIENTE = "src/test/resources/one-cliente.json";
    private static final String ONE_ATENDIMENTO = "src/test/resources/one-atendimento.json";

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void deveRetornarSucessoQuandoBuscarPetPorId() throws Exception {

        when(this.petService.findById(anyInt()))
                .thenReturn(createMockPet().get());

        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(ONE_PET, Pet.class)))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoBuscarClientePorId() throws Exception {

        when(this.clienteService.findById(anyInt()))
                .thenReturn(createMockCliente().get());

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(ONE_CLIENTE, Cliente.class)));
    }

    @Test
    public void deveRetornarSucessoQuandoBuscarAtendimentoPorId() throws Exception {

        when(this.atendimentoRepository.findById(anyInt()))
                .thenReturn(createMockAtendimento());

        mockMvc.perform(get("/atendimentos/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(ONE_ATENDIMENTO, Atendimento.class)));
    }

    @Test
    public void deveRetornarSucessoQuandoCadastrarCliente() throws Exception {

        when(this.clienteService.save(any()))
                .thenReturn(createMockCliente().get());

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createMockClienteDTO())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(getJsonContent(ONE_CLIENTE, Cliente.class)))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoCadastrarPet() throws Exception {

        when(this.petService.save(any()))
                .thenReturn(createMockPet().get());

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createMockPetDTO())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(getJsonContent(ONE_PET, Pet.class)))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoCadastrarAtendimento() throws Exception {

        when(this.atendimentoRepository.save(any()))
                .thenReturn(createMockAtendimento().get());
        when(this.clienteRepository.findById(anyInt()))
                .thenReturn(createMockCliente());
        when(this.petRepository.findById(anyInt()))
                .thenReturn(createMockPet());

        mockMvc.perform(post("/atendimentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createMockAtendimento())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(getJsonContent(ONE_ATENDIMENTO, Atendimento.class)))
                .andReturn();
    }

    private String getJsonContent(String path, Class<?> clazz) throws IOException {
        return mapper.writeValueAsString(mapper.readValue(new File(path), clazz));
    }

    private Optional<Pet> createMockPet() {
        return ofNullable(Pet.builder().id(1).nome("Teste nome").dataCadastro(LocalDate.parse("2020-07-03")).build());
    }

    private PetDTO createMockPetDTO() {
        return PetDTO.builder().nome("Teste nome").build();
    }

    private ClienteDTO createMockClienteDTO() {
        return ClienteDTO.builder().nome("Teste nome")
                .celular("(11) 99999-9999").email("teste@email.com").telefone("(11) 99999-9999")
                .build();
    }

    private Optional<Cliente> createMockCliente() {
        return ofNullable(Cliente.builder().id(1).nome("Teste nome")
                .celular("(11) 99999-9999").email("teste@email.com").telefone("(11) 99999-9999")
                .dataCadastro(LocalDate.parse("2020-07-03"))
                .build());
    }

    private Optional<Atendimento> createMockAtendimento() {
        return of(new Atendimento(1, "Teste nome", LocalDate.parse("2020-07-03"),
                Endereco.builder().id(1).rua("Rua teste").dataCadastro(LocalDate.parse("2020-07-03")).build(),
                Pet.builder().id(1).nome("Teste nome").dataCadastro(LocalDate.parse("2020-07-03")).build(),
                Cliente.builder().id(1).nome("Teste nome").dataCadastro(LocalDate.parse("2020-07-03")).build()));
    }

    @PostConstruct
    public void setupPostConstruct() {
        mapper.findAndRegisterModules();
    }

}
