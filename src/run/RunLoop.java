package run;

import gui.GameWindow;
import gui.Init;

public class RunLoop {
  private static final int FRAMES_PER_SECOND = 50;

  public static void main(String... args) {
    // Main GameFrame.
    GameFrame gf = Init.makeNewGameFrame();
    GameWindow main = Init.startGUI(gf);

    long frameDelayms = (long) ((double) 1000 / (double) FRAMES_PER_SECOND);

    boolean dontStop = true;
    while (dontStop)
    {
      main.repaint();
      gf.step();

      try
      {
        Thread.sleep(frameDelayms);
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
