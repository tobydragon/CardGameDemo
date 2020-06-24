package edu.ithaca.dragon.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ithaca.dragon.blackjack.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BlackJackController.class)
public class BlackJackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHandTest() throws Exception{
        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.SPADE, 1));
        c1.add(new Card(Card.Suit.SPADE, 13));
        BettingHand h1 = new BettingHand(c1);
        ArrayList<Card> c2 = new ArrayList<>();
        c2.add(new Card(Card.Suit.DIAMOND, 10));
        c2.add(new Card(Card.Suit.DIAMOND, 12));
        BettingHand h2 = new BettingHand(c2);
        GameStateResponse hr1 = new GameStateResponse(h1,new Hand(), BlackJack.RoundState.BETTING,21, 0,"0", 0.0);
        GameStateResponse hr2 = new GameStateResponse(h2, new Hand(), BlackJack.RoundState.BETTING, 20, 0, "0", 0.0);
        ObjectMapper mapper = new ObjectMapper();
        String test = mapper.writeValueAsString(hr1);
        String test2 = mapper.writeValueAsString(hr2);
        this.mockMvc.perform(get("/api/blackjack/0")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(test)));
        this.mockMvc.perform(get("/api/blackjack/1")).andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/api/blackjack/2")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(test2)));
    }

    @Test
    public void newGameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        BettingHand dud = new BettingHand();
        GameStateResponse hr1 = new GameStateResponse(dud, new Hand(), BlackJack.RoundState.BETTING, 0, 0, "stephen", 100.0);
        UserContainer u1 = new UserContainer("stephen", 100);
        for(int x = 3; x < 10; x++){
            this.mockMvc.perform(post("/api/blackjack/newgame").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(u1)))
                    .andExpect(status().isOk()).andExpect(content().string(equalTo(String.format("{\"text\":\"%07d\"}", x))));
            this.mockMvc.perform(get(String.format("/api/blackjack/%07d", x))).andExpect(status().isOk())
                    .andExpect(content().string(equalTo(mapper.writeValueAsString(hr1))));
        }
    }

    @Test
    public void dealTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        BettingHand dud = new BettingHand();
        GameStateResponse hr1 = new GameStateResponse(dud,dud, BlackJack.RoundState.PLAYING, 0, 0, "0", 100.0);
        String test = mapper.writeValueAsString(hr1);
        UserContainer u1 = new UserContainer("ephen", 100);
        this.mockMvc.perform(post("/api/blackjack/newgame").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(u1))).andExpect(content().string(equalTo(String.format("{\"text\":\"%07d\"}", 10))));
        MvcResult result =  this.mockMvc.perform(post("/api/blackjack/0000010/deal").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"text\":\"10.0\"}")).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        GameStateResponse hr = mapper.readValue(content, GameStateResponse.class);
        assertEquals(10.00, hr.getPlayerHand().getBet());
        if(hr.getState() == BlackJack.RoundState.PLAYING)
            this.mockMvc.perform(put("/api/blackjack/0000010/stay")).andExpect(status().isOk());
        this.mockMvc.perform(post("/api/blackjack/0000010/deal").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"text\":\"10.0\"}")).andExpect(status().isOk())
                .andExpect(content().string(not(test)));
                //mapper.readValue(content().toString(), Hand.class).getCards()
    }

    @Test
    public void hitTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        BettingHand h1 = new BettingHand();
        GameStateResponse dud = new GameStateResponse(h1, new Hand(), BlackJack.RoundState.PLAYING, 11, 0, "0", 0.0);
        dud.getPlayerHand().addCard(new Card(Card.Suit.SPADE, 1));
        this.mockMvc.perform(post("/api/blackjack/test2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        dud.getPlayerHand().addCard(new Card(Card.Suit.SPADE, 2));
        dud.setPlayerValue(13);
        this.mockMvc.perform(post("/api/blackjack/test2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        dud.getPlayerHand().addCard(new Card(Card.Suit.SPADE, 3));
        dud.setPlayerValue(16);
        this.mockMvc.perform(post("/api/blackjack/test2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        this.mockMvc.perform(post("/api/blackjack/1/hit")).andExpect(status().is4xxClientError());
    }

    @Test
    public void stayTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = this.mockMvc.perform(put("/api/blackjack/stayLose/stay")).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        GameStateResponse hr = mapper.readValue(content, GameStateResponse.class);
        assertEquals(BlackJack.RoundState.LOST_DEALER_BEATS_PLAYER, hr.getState());
    }




}
