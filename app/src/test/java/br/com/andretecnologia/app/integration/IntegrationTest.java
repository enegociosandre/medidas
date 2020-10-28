package br.com.andretecnologia.app.integration;

import br.com.andretecnologia.app.dto.ClienteDTO;
import br.com.andretecnologia.app.dto.PetDTO;
import br.com.andretecnologia.app.model.Cliente;
import br.com.andretecnologia.app.model.Pet;
import br.com.andretecnologia.app.repository.ClienteRepository;
import br.com.andretecnologia.app.repository.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void loadSampleData() throws Exception {
        petRepository.saveAll(
                Arrays.asList(Pet.builder().nome("Teste nome").build(), Pet.builder().nome("Teste nome").build()));
        clienteRepository.saveAll(
                Arrays.asList(Cliente.builder().nome("Teste nome").build(), Cliente.builder().nome("Teste nome").build()));
    }

    @Test
    public void deveRetornarSucessoQuandoBuscarClientePorId() throws Exception {

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath( "$.nome", is("Teste nome")))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoBuscarPetPorId() throws Exception {

        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath( "$.nome", is("Teste nome")))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoCadastrarPet() throws Exception {

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createMockPetDTO())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath( "$.nome", is("Teste nome")))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoDeletarPet() throws Exception {

        mockMvc.perform(delete("/pets/2"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoCadastrarCliente() throws Exception {

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(createMockClienteDTO())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath( "$.nome", is("Teste nome")))
                .andReturn();
    }

    @Test
    public void deveRetornarSucessoQuandoDeletarCliente() throws Exception {

        mockMvc.perform(delete("/clientes/2"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    private PetDTO createMockPetDTO() {
        return PetDTO.builder().nome("Teste nome").build();
    }

    private ClienteDTO createMockClienteDTO() {
        return ClienteDTO.builder().nome("Teste nome")
                .celular("(11) 99999-9999").email("teste@email.com").telefone("(11) 99999-9999")
                .build();
    }

    @PostConstruct
    public void setupPostConstruct() {
        mapper.findAndRegisterModules();
    }

}

