package co.uk.code.challenge.samuel.catalano.zilch;

import co.uk.code.challenge.samuel.catalano.zilch.controller.AccountController;
import co.uk.code.challenge.samuel.catalano.zilch.controller.CardController;
import co.uk.code.challenge.samuel.catalano.zilch.controller.TransferController;
import co.uk.code.challenge.samuel.catalano.zilch.controller.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZilchApplicationTests {

    @Autowired
    private AccountController accountController;

    @Autowired
    private UserController userController;

    @Autowired
    private TransferController transferController;

    @Autowired
    private CardController cardController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void contextLoads() {
        assertThat(this.accountController).isNotNull();
        assertThat(this.userController).isNotNull();
        assertThat(this.transferController).isNotNull();
        assertThat(this.cardController).isNotNull();
    }

    @Test
    public void testGetAccountById() throws Exception {
        this.mockMvc.perform(get("/zilch/account/get-by-id/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAccountByUserDocument() throws Exception {
        this.mockMvc.perform(get("/zilch/account/get-by-user-document/G418FG2Z"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception {
        this.mockMvc.perform(get("/zilch/user/get-by-id/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserByName() throws Exception {
        this.mockMvc.perform(get("/zilch/user/get-by-name/Samuel Catalano"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserByNameWithoutLastName() throws Exception {
        this.mockMvc.perform(get("/zilch/user/get-by-name/Samuel"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetUserByDocumentNumber() throws Exception {
        this.mockMvc.perform(get("/zilch/user/get-by-document/G418FG2Z"))
                .andExpect(status().isOk());
    }

    @Test
    public void testMakeATransferBetweenAccounts() throws Exception {
        final String request = "{\"origin\":10972647,\"destiny\":33972913,\"value\":150}";
        this.mockMvc.perform(post("/zilch/transfer/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void testMakeATransferBetweenAccountsWrongPath() throws Exception {
        final String request = "{\"origin\":10972647,\"destiny\":33972913,\"value\":150}";
        this.mockMvc.perform(post("/zilch/transfer/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGenerateNewCard() throws Exception {
        this.mockMvc.perform(post("/zilch/card/new-card/33972913"))
                .andExpect(status().isOk());
    }
}