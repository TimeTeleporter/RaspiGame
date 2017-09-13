/*
 * Copyright (C) 2013-2017 by Thomas Jampen <thomas.jampen@gymkirchenfeld.ch>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY); without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.kinet.ladderboard;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import java.util.ArrayList;
import java.util.List;

public class LadderBoard {

    public static final int NUM_BUTTONS = 4;
    public static final int NUM_LEDS = 8;
    
    static final Pin LED_GREEN_PIN = RaspiPin.GPIO_08;
    static final Pin LED_RED_PIN = RaspiPin.GPIO_09;
    
    public static final List<LadderBoardButton> BUTTONS = new ArrayList();
    public static final List<LadderBoardLed> LEDS = new ArrayList();
    public final LadderBoardLed greenLed;
    public final LadderBoardLed redLed;
    
    static final Pin[] BUTTON_PINS = {RaspiPin.GPIO_13,
                                      RaspiPin.GPIO_12,
                                      RaspiPin.GPIO_10,
                                      RaspiPin.GPIO_11};
    
    static final Pin[] LED_PINS = {RaspiPin.GPIO_00,
                                   RaspiPin.GPIO_01,
                                   RaspiPin.GPIO_02,
                                   RaspiPin.GPIO_03,
                                   RaspiPin.GPIO_04,
                                   RaspiPin.GPIO_05,
                                   RaspiPin.GPIO_06,
                                   RaspiPin.GPIO_07};
    
    private GpioController gpio;

    public LadderBoard() {
        gpio = GpioFactory.getInstance();

        // setup buttons
        for (int i = 0; i < NUM_BUTTONS; i++) {
            String name = "Button-" + (char)(i + 65);
            BUTTONS.add(new LadderBoardButton(gpio.provisionDigitalInputPin(BUTTON_PINS[i], name), i));
        }
        
        // setup leds (default state off)
        for (int i = 0; i < NUM_LEDS; i++) {
            String name = "LED-" + i;
            LEDS.add(new LadderBoardLed(gpio.provisionDigitalOutputPin(LED_PINS[i], name), i, LadderBoardLed.STATE_OFF));
        }
        
        // setup additional leds
        greenLed = new LadderBoardLed(gpio.provisionDigitalOutputPin(LED_GREEN_PIN, "LED-G"), 8, LadderBoardLed.STATE_OFF);
        redLed = new LadderBoardLed(gpio.provisionDigitalOutputPin(LED_RED_PIN, "LED-R"), 9, LadderBoardLed.STATE_OFF);
    }
    
    public void addLadderBoardButtonListener(LadderBoardButtonListener listener) {
        for (LadderBoardButton button : BUTTONS) {
            button.addLadderBoardButtonListener(listener);
        }
    }
    
    public void removeLadderBoardButtonListener(LadderBoardButtonListener listener) {
        for (LadderBoardButton button : BUTTONS) {
            button.removeLadderBoardButtonListener(listener);
        }
    }
    
    public void removeAllLadderBoardButtonListeners() {
        for (LadderBoardButton button : BUTTONS) {
            button.removeAllLadderBoardButtonListeners();
        }
    }
    
    public void shutdown() {
        gpio.shutdown();
    }
    
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void ledSlide() {
        for (LadderBoardLed led: LEDS) {
        
            led.on();
            sleep(125);
            led.off();
        }
    }
}
