package edu.ithaca.dragon.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ithaca.dragon.blackjack.*;
import org.assertj.core.util.diff.ChangeDelta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.Dictionary;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;


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
        HandReturn hr1 = new HandReturn(h1,new Hand(), BlackJack.BlackJackState.BLACKJACK,21,"0");
        HandReturn hr2 = new HandReturn(h2, new Hand(), BlackJack.BlackJackState.UNDER, 20, "0");
        ObjectMapper mapper = new ObjectMapper();
        String test = mapper.writeValueAsString(hr1);
        String test2 = mapper.writeValueAsString(hr2);
        this.mockMvc.perform(get("/api/blackjack/0")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo(test)));
        this.mockMvc.perform(get("/api/blackjack/1")).andDo(print()).andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/api/blackjack/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(equalTo(test2)));
    }

    @Test
    public void newGameTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Hand dud = new Hand();
        HandReturn hr1 = new HandReturn(dud, dud, BlackJack.BlackJackState.UNDER, 0, "Stephen");
        for(int x = 3; x < 10; x++){
            this.mockMvc.perform(post("/api/blackjack/newgame").contentType(MediaType.TEXT_PLAIN).content("Stephen"))
                    .andExpect(status().isOk()).andExpect(content().string(equalTo(String.format("{\"text\":\"%07d\"}", x))));
            this.mockMvc.perform(get(String.format("/api/blackjack/%07d", x))).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(equalTo(mapper.writeValueAsString(hr1))));
        }
    }

    @Test
    public void dealTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Hand dud = new Hand();
        HandReturn hr1 = new HandReturn(dud,dud,BlackJack.BlackJackState.UNDER, 0, "0");
        String test = mapper.writeValueAsString(hr1);
        this.mockMvc.perform(post("/api/blackjack/test/deal")).andExpect(status().isOk());
        this.mockMvc.perform(post("/api/blackjack/test/deal")).andExpect(status().isOk())
                .andExpect(content().string(not(test)));
                //mapper.readValue(content().toString(), Hand.class).getCards()
    }

    @Test
    public void hitTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Hand h1 = new Hand();
        HandReturn dud = new HandReturn(h1, new Hand(), BlackJack.BlackJackState.UNDER, 11, "0");
        dud.getPlayerHand().addCard(new Card(Card.Suit.SPADE, 1));
        this.mockMvc.perform(post("/api/blackjack/test2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        dud.getPlayerHand().addCard(new Card(Card.Suit.SPADE, 2));
        dud.setValue(13);
        this.mockMvc.perform(post("/api/blackjack/test2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        dud.getPlayerHand().addCard(new Card(Card.Suit.SPADE, 3));
        dud.setValue(16);
        this.mockMvc.perform(post("/api/blackjack/test2/hit")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(dud))));
        this.mockMvc.perform(post("/api/blackjack/1/hit")).andExpect(status().is4xxClientError());
    }

    @Test
    public void stayTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = this.mockMvc.perform(post("/api/blackjack/stayLose/stay")).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        HandReturn hr = mapper.readValue(content, HandReturn.class);
        assertEquals(BlackJack.WinState.LOSE, hr.getWinState());
    }


}
