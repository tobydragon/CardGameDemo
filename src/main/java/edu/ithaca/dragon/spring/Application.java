package edu.ithaca.dragon.spring;

import edu.ithaca.dragon.blackjack.Card;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/card")
	public Card card(@RequestParam(value = "suit", defaultValue = "spades") String suit, @RequestParam(value = "value", defaultValue = "2")String value){
		Card returnCard = null;
		if(suit.charAt(suit.length()-1) == 's')
			suit = suit.substring(0, suit.length()-1);
		int val = 2;
		try{
			val = Integer.parseInt(value);
		}
		catch(Exception e){}
		switch (suit.toLowerCase()){
			case "diamond":
				returnCard = new Card(Card.Suit.DIAMOND, val);
				break;
			case "club":
				returnCard = new Card(Card.Suit.CLUB, val);
				break;
			case "heart":
				returnCard = new Card(Card.Suit.HEART, val);
				break;
			default:
				returnCard = new Card(Card.Suit.SPADE, val);
				break;
		}
		return returnCard;
	}


}
