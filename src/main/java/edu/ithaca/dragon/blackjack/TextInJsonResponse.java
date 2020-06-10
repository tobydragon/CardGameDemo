package edu.ithaca.dragon.blackjack;

public class TextInJsonResponse {
    private String text;

    public TextInJsonResponse() {
    }

    public TextInJsonResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
