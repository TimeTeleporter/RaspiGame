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
    private boolean difficulty = false;
    private boolean ingame = false;
    private boolean exit = false;
    private int buttonsPressed = -1;
    private int score = 0;
    private int speed;
    
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
        // If you play, then handle buttonsEvents like the following
        if (difficulty){
            if (getButtonPressed(event, BUTTON_A)){
                speed = 250;
                System.out.println("Button A pressed \n Speed now" + speed);
            }
            else if (getButtonPressed(event, BUTTON_B)){
                speed = 500;
                System.out.println("Button B pressed \n Speed now" + speed);
            }
            else if (getButtonPressed(event, BUTTON_C)){
                speed = 1000;
                System.out.println("Button C pressed \n Speed now" + speed);
            }
            else if (getButtonPressed(event, BUTTON_D)){
                speed = 2000;
                System.out.println("Button D pressed \n Speed now" + speed);
            }
            difficulty = false;
        }
        if (ingame) {
            if (getButtonPressed(event, BUTTON_A) && getLedOn(0)){
                System.out.println("Button A pressed");
                turnLedOff(0);
            }
            else if (getButtonPressed(event, BUTTON_B) && getLedOn(2)){
                System.out.println("Button B pressed");
                turnLedOff(2);
            }
            else if (getButtonPressed(event, BUTTON_C) && getLedOn(4)){
                System.out.println("Button C pressed");
                turnLedOff(4);
            }
            else if (getButtonPressed(event, BUTTON_D) && getLedOn(6)){
                System.out.println("Button D pressed");
                turnLedOff(6);
            }
        score = score + 1;
        }
        if (exit){
            exit = false;
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
    }
    // Returns true if the pressed button corresponds to the wanted button.
    private boolean getButtonPressed(LadderBoardButtonEvent event, int button){
        return event.getButton().getIndex() == button;
    }
    // Returns true if the wanted colour fits with the Led which's on.
    private boolean getLedOn(int light){
        return LadderBoard.LEDS.get(light).isOn()|| LadderBoard.LEDS.get(light+1).isOn();
    }
    // 
    
    private void run() {
        int rounds = 32;
        int randomNumber;
        
        /*int flashes = score;*/
        ladderBoard.redLed.off();
        ladderBoard.greenLed.on();
        while (difficulty){
            for (int i = 0; i < LadderBoard.NUM_LEDS; i++){
                LadderBoard.LEDS.get(i).on();
            }
        }
        while (running) {
            // Rundenzähler
            ingame = true;
            for(int i = 0; i < rounds; i++){
                randomNumber = random.nextInt(LadderBoard.NUM_LEDS);
                LadderBoard.LEDS.get(randomNumber).on();
                LadderBoard.sleep(speed);
            }
            ingame = false;
            exit = true;
            clearLeds();
            displayPercent(rounds);
            clearLeds();
            animation(25, 8);
            // ask for exit game
            if (exit = true) {
                running = false;
            
            }
        }
        ladderBoard.greenLed.off();
        ladderBoard.shutdown();
        System.exit(0);
    }
    // Prozentualanzeige
    private void displayPercent (int rounds){
        int percent;
        percent = (int)Math.ceil(score * 8 / rounds);
        for(int i = 0; i < percent; i++){
            LadderBoard.LEDS.get(i).on();
        }
        score = 0;
        LadderBoard.sleep(5000);
    }
    private void clearLeds (){
        for(int i = 0; i < LadderBoard.NUM_LEDS; i++){
            LadderBoard.LEDS.get(i).off();
            LadderBoard.sleep(125);
        }
    }
    private void animation (int time, int rounds){
        for (int i = 0; i < rounds; i++){
            ladderBoard.LEDS.get(3).on();
            ladderBoard.LEDS.get(4).on();
            LadderBoard.sleep(time);
            for(int j = 0; j < 3; j++){
                ladderBoard.LEDS.get(2-j).on();
                ladderBoard.LEDS.get(3+j).on();
                LadderBoard.sleep(time);
                ladderBoard.LEDS.get(3-j).off();
                ladderBoard.LEDS.get(4+j).off();
            }
            LadderBoard.sleep(time);
            ladderBoard.LEDS.get(0).off();
            ladderBoard.LEDS.get(7).off();
            // 150
        }
    }
    public static void main(String[] args) {
        new RaspiGame();
    }
    
}
