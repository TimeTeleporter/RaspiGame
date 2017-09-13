/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  ControlGpioExample.java  
 * 
 * This file is part of the Pi4J project. More information about 
 * this project can be found here:  http://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2015 Pi4J
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * This example code demonstrates how to perform simple state
 * control of a GPIO pin on the Raspberry Pi.  
 * 
 * @author Robert Savage
 */
public class ControlGpioExample {
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("<--Pi4J--> GPIO Control Example ... started.");
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pi0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput pi7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput piG = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyLED", PinState.LOW);
        final GpioPinDigitalOutput piR = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "MyLED", PinState.LOW);

        // set shutdown state for this pin
        pi0.setShutdownOptions(true, PinState.LOW);
        pi1.setShutdownOptions(true, PinState.LOW);
        pi2.setShutdownOptions(true, PinState.LOW);
        pi3.setShutdownOptions(true, PinState.LOW);
        pi4.setShutdownOptions(true, PinState.LOW);
        pi5.setShutdownOptions(true, PinState.LOW);
        pi6.setShutdownOptions(true, PinState.LOW);
        pi7.setShutdownOptions(true, PinState.LOW);
        piG.setShutdownOptions(true, PinState.LOW);
        piR.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> Program started");

        Thread.sleep(5000);
        
        pi0.high();
        System.out.println("--> 0 state should be: ON");

        Thread.sleep(3000);
        
        pi1.high();
        System.out.println("--> 1 state should be: ON");

        Thread.sleep(3000);
        
        pi2.high();
        System.out.println("--> 2 state should be: ON");

        Thread.sleep(3000);
        
        pi3.high();
        System.out.println("--> 3 state should be: ON");

        Thread.sleep(3000);
        
        pi4.high();
        System.out.println("--> 4 state should be: ON");

        Thread.sleep(3000);
        
        pi5.high();
        System.out.println("--> 5 state should be: ON");

        Thread.sleep(3000);
        
        pi6.high();
        System.out.println("--> 6 state should be: ON");

        Thread.sleep(3000);
        
        pi7.high();
        System.out.println("--> 7 state should be: ON");

        Thread.sleep(3000);
        
        piG.high();
        System.out.println("--> G state should be: ON");

        Thread.sleep(3000);
        
        piR.high();
        System.out.println("--> R state should be: ON");

        Thread.sleep(3000);
        
        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }
}