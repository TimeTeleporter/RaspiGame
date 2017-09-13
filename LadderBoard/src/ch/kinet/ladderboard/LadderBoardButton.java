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

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.util.ArrayList;
import java.util.List;

public class LadderBoardButton {
    private final GpioPinDigitalInput gpioInput;
    private final List<LadderBoardButtonListener> ladderBoardListeners;
    private final List<GpioPinListenerDigital> gpioListeners;
    private final int index;
    
    public LadderBoardButton(GpioPinDigitalInput gpioInput, int index) {
        this.gpioInput = gpioInput;
        this.index = index;
        
        ladderBoardListeners = new ArrayList();
        gpioListeners = new ArrayList();
        
        this.gpioInput.setPullResistance(PinPullResistance.PULL_UP);
    }
    
    public int getIndex() {
        return index;
    }
    
    void addLadderBoardButtonListener(final LadderBoardButtonListener listener) {
        GpioPinListenerDigital gpioListener = new GpioPinListenerDigital() {

            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                LadderBoardButtonEvent jjEvent = new LadderBoardButtonEvent(event);
                
                if (event.getState().isLow()) {
                    listener.buttonPressed(jjEvent);
                }
                else {
                    listener.buttonReleased(jjEvent);
                }
            }
        };
        
        // save listeners
        gpioListeners.add(gpioListener);
        ladderBoardListeners.add(listener);
        
        gpioInput.addListener(gpioListener);
    }
    
    void removeLadderBoardButtonListener(final LadderBoardButtonListener listener) {
        int current_index = ladderBoardListeners.indexOf(listener);
        
        if (current_index < 0) {
            // cannot remove unknown listener
            return;
        }
        
        gpioInput.removeListener(gpioListeners.get(current_index));
    }
    
    void removeAllLadderBoardButtonListeners() {
        gpioInput.removeAllListeners();
    }
}
