/*
 * Copyright (C) 2017 by Thomas Jampen <thomas.jampen@gymkirchenfeld.ch>
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

package ch.kinet.jat.verify;

import ch.kinet.ladderboard.LadderBoard;
import ch.kinet.ladderboard.LadderBoardButtonEvent;
import ch.kinet.ladderboard.LadderBoardButtonListener;

public class VerifySoldering {
    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_C = 2;
    public static final int BUTTON_D = 3;
    
    private LadderBoard ladderBoard;
    private LadderBoardButtonListener mainButtonListener;
    private boolean running = true;
    private int buttonsPressed = -1;
    
    public VerifySoldering() {
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
        
        // add our own listener for the game selection
        ladderBoard.addLadderBoardButtonListener(mainButtonListener);
    }
    
    private void run() {
        ladderBoard.redLed.off();
        ladderBoard.greenLed.on();
        
        while (running) {
            LadderBoard.sleep(500);
        }
        
        ladderBoard.greenLed.off();
        
        ladderBoard.shutdown();
        System.exit(0);
    }
    
    protected void handleButtonEvent(LadderBoardButtonEvent event) {
        if (event.getButton().getIndex() == BUTTON_A) {
            System.out.println("Button A pressed");
            ladderBoard.redLed.flash(3);
            ladderBoard.ledSlide();
        }
        else if (event.getButton().getIndex() == BUTTON_B) {
            System.out.println("Button B pressed");
            ladderBoard.redLed.flash(3);
            ladderBoard.ledSlide();
        }
        else if (event.getButton().getIndex() == BUTTON_C) {
            System.out.println("Button C pressed");
            ladderBoard.redLed.flash(3);
            ladderBoard.ledSlide();
        }
        else if (event.getButton().getIndex() == BUTTON_D) {
            System.out.println("Button D pressed");
            ladderBoard.redLed.flash(3);
            ladderBoard.ledSlide();
        }
    }
            
    public static void main(String[] args) {
        new VerifySoldering();
    }
}
