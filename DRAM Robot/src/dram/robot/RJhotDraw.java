package dram.robot;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import CH.ifa.draw.samples.javadraw.JavaDrawApp;

/*
* Created on 2005-01-31
*
* TODO To change the template for this generated file go to
* Window - Preferences - Java - Code Style - Code Templates
*/

/**
* @author rachedsa
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Style - Code Templates
*/
public class RJhotDraw {


    static int keyInput[] = {
              KeyEvent.VK_H,
              KeyEvent.VK_E,
              KeyEvent.VK_L,
              KeyEvent.VK_L,
              KeyEvent.VK_O
          };
    RJhotDraw(){
        //app = new JavaDrawApp();

}
    public static void main(String[] args)throws AWTException,IOException {
        JavaDrawApp window = new JavaDrawApp();
        window.open();

        Robot robot = new Robot();
        
        robot.delay(1000);
       
        //Pour se positionner sur le rectangle dans le toolbar et activer le boutton
        robot.mouseMove(8,170);
        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(1000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        //Pour se positionner dans le composant ou dessiner le rectancle
        robot.mouseMove(80,170);
        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(1000);
        robot.mouseMove(200,300);
        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(1000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        // Pour se positionner dans le menu et l'activer
        
        robot.mouseMove(6,30);
        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(1000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        //Pour activer exit et fermer l'application

        robot.mouseMove(10,180);
        robot.delay(1000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(1000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(1000);

        System.out.println("fini");
    }

} 
