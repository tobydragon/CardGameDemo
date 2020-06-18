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
import java.util.HashMap;
import java.util.Map;

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
        HandReturn hr1 = new HandReturn(h1,new Hand(), null,21, 0,"0", 0.0);
        HandReturn hr2 = new HandReturn(h2, new Hand(), null, 20, 0, "0", 0.0);
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
        HandReturn hr1 = new HandReturn(dud, new Hand(), null, 0, 0, "stephen", 0.0);
        for(int x = 3; x < 10; x++){
            this.mockMvc.perform(post("/api/blackjack/newgame").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"text\":\"stephen\"}"))
                    .andExpect(status().isOk()).andExpect(content().string(equalTo(String.format("{\"text\":\"%07d\"}", x))));
            this.mockMvc.perform(get(String.format("/api/blackjack/%07d", x))).andExpect(status().isOk())
                    .andExpect(content().string(equalTo(mapper.writeValueAsString(hr1))));
        }
    }

    @Test
    public void dealTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        BettingHand dud = new BettingHand();
        HandReturn hr1 = new HandReturn(dud,dud,null, 0, 0, "0", 0.0);
        String test = mapper.writeValueAsString(hr1);
        this.mockMvc.perform(post("/api/blackjack/test/deal")).andExpect(status().isOk());
        this.mockMvc.perform(post("/api/blackjack/test/deal")).andExpect(status().isOk())
                .andExpect(content().string(not(test)));
                //mapper.readValue(content().toString(), Hand.class).getCards()
    }

    @Test
    public void hitTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        BettingHand h1 = new BettingHand();
        HandReturn dud = new HandReturn(h1, new Hand(), BlackJack.RoundState.PLAYING, 11, 0, "0", 0.0);
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
        HandReturn hr = mapper.readValue(content, HandReturn.class);
        assertEquals(BlackJack.RoundState.LOST_DEALER_BEATS_PLAYER, hr.getState());
    }




}
