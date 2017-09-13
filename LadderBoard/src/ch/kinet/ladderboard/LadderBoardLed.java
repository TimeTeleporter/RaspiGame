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

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;

public class LadderBoardLed {
    public static final boolean STATE_ON = false;
    public static final boolean STATE_OFF = true;
    
    public static final int DEFAULT_PULSE_DURATION = 100;
    
    private final GpioPinDigitalOutput gpioOutput;
    private final int index;
    private boolean on;

    public LadderBoardLed(GpioPinDigitalOutput gpioOutput, int index, boolean defaultState) {
        this.gpioOutput = gpioOutput;
        this.index = index;
        
        PinState shutdownState = defaultState ? PinState.LOW : PinState.HIGH;
        PinState initialState = defaultState ? PinState.LOW : PinState.HIGH;
        this.gpioOutput.setState(initialState);
        this.gpioOutput.setShutdownOptions(true, shutdownState, PinPullResistance.OFF);
        
        if (defaultState == STATE_ON) {
            on = true;
        }
        else {
            on = false;
        }
    }
    
    public int getIndex() {
        return index;
    }
    
    public void on() {
        gpioOutput.high();
        on = true;
    }
    
    public void off() {
        gpioOutput.low();
        on = false;
    }
    
    public boolean isOn() {
        return on;
    }
    
    public void pulse() {
        this.pulse(DEFAULT_PULSE_DURATION);
    }
    
    public void pulse(long duration) {
        gpioOutput.pulse(duration);
        LadderBoard.sleep((int)duration);
    }
    
    public void flash(int numFlashes) {
        for (int i = 0; i < numFlashes; i++) {
            this.pulse();
            LadderBoard.sleep(DEFAULT_PULSE_DURATION);
        }
    }
}
