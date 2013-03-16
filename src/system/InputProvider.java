package system;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/*
 * Remember to call super() as an implementor of PlayerCharacter, in order to
 * register as a listener.
 */
public class InputProvider implements MouseListener, MouseWheelListener, MouseMotionListener {
//     Not final; let's leave the ability to change them in-game.
  private static Integer upKeycode = KeyEvent.VK_UP;
  private static Integer downKeycode = KeyEvent.VK_DOWN;
  private static Integer leftKeycode = KeyEvent.VK_LEFT;
  private static Integer rightKeycode = KeyEvent.VK_RIGHT;
  private static Integer fire1Keycode = KeyEvent.VK_Z;
  private static Integer fire2Keycode = KeyEvent.VK_X;
  
  public static volatile boolean isUpPressed = false;
  public static volatile boolean isDownPressed = false;
  public static volatile boolean isLeftPressed = false;
  public static volatile boolean isRightPressed = false;
  public static volatile boolean isFire1Pressed = false;
  public static volatile boolean isFire2Pressed = false;
  
  private enum KeyChange {
    UP_PRESSED, UP_RELEASED,
    DOWN_PRESSED, DOWN_RELEASED,
    LEFT_PRESSED, LEFT_RELEASED,
    RIGHT_PRESSED, RIGHT_RELEASED,
    FIRE1_PRESSED, FIRE1_RELEASED,
    FIRE2_PRESSED, FIRE2_RELEASED,
  }
  
  public InputProvider(JComponent c) {
    c.getInputMap().put(KeyStroke.getKeyStroke(upKeycode, 0, false), KeyChange.UP_PRESSED);
    c.getInputMap().put(KeyStroke.getKeyStroke(upKeycode, 0, true), KeyChange.UP_RELEASED);
    c.getInputMap().put(KeyStroke.getKeyStroke(downKeycode, 0, false), KeyChange.DOWN_PRESSED);
    c.getInputMap().put(KeyStroke.getKeyStroke(downKeycode, 0, true), KeyChange.DOWN_RELEASED);
    c.getInputMap().put(KeyStroke.getKeyStroke(leftKeycode, 0, false), KeyChange.LEFT_PRESSED);
    c.getInputMap().put(KeyStroke.getKeyStroke(leftKeycode, 0, true), KeyChange.LEFT_RELEASED);
    c.getInputMap().put(KeyStroke.getKeyStroke(rightKeycode, 0, false), KeyChange.RIGHT_PRESSED);
    c.getInputMap().put(KeyStroke.getKeyStroke(rightKeycode, 0, true), KeyChange.RIGHT_RELEASED);
    c.getInputMap().put(KeyStroke.getKeyStroke(fire1Keycode, 0, false), KeyChange.FIRE1_PRESSED);
    c.getInputMap().put(KeyStroke.getKeyStroke(fire1Keycode, 0, true), KeyChange.FIRE1_RELEASED);
    c.getInputMap().put(KeyStroke.getKeyStroke(fire2Keycode, 0, false), KeyChange.FIRE2_PRESSED);
    c.getInputMap().put(KeyStroke.getKeyStroke(fire2Keycode, 0, true), KeyChange.FIRE2_RELEASED);

    c.getActionMap().put(KeyChange.UP_PRESSED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isUpPressed = true;
      }
    });
    c.getActionMap().put(KeyChange.UP_RELEASED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isUpPressed = false;
      }
    });
    c.getActionMap().put(KeyChange.DOWN_PRESSED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isDownPressed = true;
      }
    });
    c.getActionMap().put(KeyChange.DOWN_RELEASED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isDownPressed = false;
      }
    });
    c.getActionMap().put(KeyChange.LEFT_PRESSED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isLeftPressed = true;
      }
    });
    c.getActionMap().put(KeyChange.LEFT_RELEASED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isLeftPressed = false;
      }
    });
    c.getActionMap().put(KeyChange.RIGHT_PRESSED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isRightPressed = true;
      }
    });
    c.getActionMap().put(KeyChange.RIGHT_RELEASED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isRightPressed = false;
      }
    });
    c.getActionMap().put(KeyChange.FIRE1_PRESSED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isFire1Pressed = true;
      }
    });
    c.getActionMap().put(KeyChange.FIRE1_RELEASED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isFire1Pressed = false;
      }
    });
    c.getActionMap().put(KeyChange.FIRE2_PRESSED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isFire2Pressed = true;
      }
    });
    c.getActionMap().put(KeyChange.FIRE2_RELEASED, new AbstractAction() {
      @Override
      public synchronized void actionPerformed(ActionEvent e) {
        isFire2Pressed = false;
      }
    });
  }
  
  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
