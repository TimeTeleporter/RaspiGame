/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.immanuel.albrecht.game;

import ch.kinet.ladderboard.LadderBoard;
import ch.kinet.ladderboard.LadderBoardButtonEvent;
import ch.kinet.ladderboard.LadderBoardButtonListener;
import ch.kinet.ladderboard.LadderBoardLed;
import java.util.Random;

/**
 *
 * @author immanuel
 */
public class RaspiGame {
    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_C = 2;
    public static final int BUTTON_D = 3;
    
    private LadderBoard ladderBoard;
    private LadderBoardButtonListener mainButtonListener;
    private Random random;
    private boolean running = true;
    private int buttonsPressed = -1;
    private int score = 0;
    
    
    public RaspiGame() {
        init();
        run();
    }
    
    
    private void init() {
        ladderBoard = new LadderBoard();
        mainButtonListener = new LadderBoardButtonListener() {

            @Override
            public void buttonPressed(LadderBoardButtonEvent event) {
                handleButtonEvent(event);
            }

            @Override
            public void buttonReleased(LadderBoardButtonEvent event) {
                // nothing to do here
            }
        };
    }
        
    protected void handleButtonEvent(LadderBoardButtonEvent event) {
        
        if (event.getButton().getIndex() == BUTTON_A && (LadderBoard.LEDS.get(0).isOn()|| LadderBoard.LEDS.get(1).isOn())){
            System.out.println("Button A pressed");
            turnLedOff(0);
        }
        else if (event.getButton().getIndex() == BUTTON_B && (LadderBoard.LEDS.get(2).isOn()|| LadderBoard.LEDS.get(3).isOn())){
            System.out.println("Button B pressed");
            turnLedOff(0);
        }
        else if (event.getButton().getIndex() == BUTTON_C && (LadderBoard.LEDS.get(4).isOn()|| LadderBoard.LEDS.get(5).isOn())){
            System.out.println("Button C pressed");
            turnLedOff(0);
        }
        else if (event.getButton().getIndex() == BUTTON_D && (LadderBoard.LEDS.get(6).isOn()|| LadderBoard.LEDS.get(7).isOn())){
            System.out.println("Button D pressed");
            turnLedOff(0);
        }
    }
    
    private void turnLedOff(int value) {
        if(LadderBoard.LEDS.get(value).isOn()){
            LadderBoard.LEDS.get(value).off();
            System.out.println("Led " + value + " cleared");
        }
        else {
            LadderBoard.LEDS.get((value+1)).off();
            System.out.println("Led " + (value+1) + " cleared");
        }
        score = score + 1;
    }
    
    private void run() {
        int rounds = 32;
        int randomNumber;
        int flashes = score;
        
        ladderBoard.redLed.off();
        ladderBoard.greenLed.on();
        
        while (running) {
            for(int i = 0; i < rounds; i++){
                randomNumber = random.nextInt(LadderBoard.NUM_LEDS);
                LadderBoard.LEDS.get(randomNumber).on();
                
                LadderBoard.sleep(6000);
            }
            for(int i = 0; i < score; i++){
                ladderBoard.ledSlide();
                LadderBoard.sleep(333);
            }
            LadderBoard.sleep(500);
        }
        
        ladderBoard.greenLed.off();
        
        
        
        ladderBoard.shutdown();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        new RaspiGame();
    }
    
}
