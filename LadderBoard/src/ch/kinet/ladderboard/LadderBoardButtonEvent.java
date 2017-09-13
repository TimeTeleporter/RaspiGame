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

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import java.util.Arrays;

public class LadderBoardButtonEvent {
    private GpioPinDigitalStateChangeEvent event;

    LadderBoardButtonEvent(GpioPinDigitalStateChangeEvent event) {
        this.event = event;
    }
    
    public LadderBoardButton getButton() {        
        return LadderBoard.BUTTONS.get(Arrays.asList(LadderBoard.BUTTON_PINS).indexOf(event.getPin().getPin()));
    }
    
    public boolean isButtonPressed() {
        return event.getState().isLow();
    }
    
    public boolean isButtonReleased() {
        return event.getState().isHigh();
    }
}
