package edu.ithaca.dragon.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ithaca.dragon.blackjack.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Hand h1 = new Hand(c1);
        ArrayList<Card> c2 = new ArrayList<>();
        c2.add(new Card(Card.Suit.DIAMOND, 10));
        c2.add(new Card(Card.Suit.DIAMOND, 12));
        Hand h2 = new Hand(c2);
        ObjectMapper mapper = new ObjectMapper();
        String test = mapper.writeValueAsString(h1);
        String test2 = mapper.writeValueAsString(h2);
        this.mockMvc.perform(get("/api/blackjack/0")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo(test)));
        this.mockMvc.perform(get("/api/blackjack/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));
        this.mockMvc.perform(get("/api/blackjack/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo(test2)));
    }

    @Test
    public void newGameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Hand dud = new Hand();
        for(int x = 3; x < 10; x++){
            this.mockMvc.perform(post("/api/blackjack/newgame").content("Stephen"))
                    .andExpect(status().isOk()).andExpect(content().string(equalTo(String.format("%07d", x))));
            this.mockMvc.perform(get(String.format("/api/blackjack/%07d", x))).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        }
    }

    @Test
    public void dealTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Hand dud = new Hand();
        dud.addCard(new Card(Card.Suit.SPADE, 2));
        dud.addCard(new Card(Card.Suit.SPADE, 8));
        String test = mapper.writeValueAsString(dud);
        this.mockMvc.perform(post("/api/blackjack/test/deal")).andExpect(status().isOk());
        this.mockMvc.perform(post("/api/blackjack/test/deal")).andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("h")));
                //mapper.readValue(content().toString(), Hand.class).getCards()
    }

    @Test
    public void hitTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Hand dud = new Hand();
        dud.addCard(new Card(Card.Suit.SPADE, 1));
        this.mockMvc.perform(post("/api/blackjack/2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        dud.addCard(new Card(Card.Suit.SPADE, 2));
        this.mockMvc.perform(post("/api/blackjack/2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        dud.addCard(new Card(Card.Suit.SPADE, 3));
        this.mockMvc.perform(post("/api/blackjack/2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
    }
}
