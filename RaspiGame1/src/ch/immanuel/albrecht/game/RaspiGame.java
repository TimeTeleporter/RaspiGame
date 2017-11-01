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
        random = new Random();
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
        
        ladderBoard.addLadderBoardButtonListener(mainButtonListener);
    }
        
    protected void handleButtonEvent(LadderBoardButtonEvent event) {
        
        if (getButtonPressed(event, BUTTON_A, 0)){
            System.out.println("Button A pressed");
            turnLedOff(0);
        }
        else if (getButtonPressed(event, BUTTON_B, 2)){
            System.out.println("Button B pressed");
            turnLedOff(2);
        }
        else if (getButtonPressed(event, BUTTON_C, 4)){
            System.out.println("Button C pressed");
            turnLedOff(4);
        }
        else if (getButtonPressed(event, BUTTON_D, 6)){
            System.out.println("Button D pressed");
            turnLedOff(6);
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
    // Returns true if the pressed button corresponds to the LED of its colour.
    private boolean getButtonPressed(LadderBoardButtonEvent event, int button, int light){
        return event.getButton().getIndex() == button && (LadderBoard.LEDS.get(light).isOn()|| LadderBoard.LEDS.get(light+1).isOn());
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
