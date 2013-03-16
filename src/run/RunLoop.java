package run;

import gui.GameWindow;
import gui.Init;

public class RunLoop {
  private static final int FRAMES_PER_SECOND = 30;

  public static void main(String... args) {
    // Main GameFrame.
    GameFrame gf = Init.makeNewGameFrame();
    GameWindow main = Init.startGUI(gf);
    // Image bufferImage = main.createImage(main.getWidth(), main.getHeight());
    // main.setBufferImage(bufferImage);

    // DEBUG
    long frameDelayms = (long) ((double) 1000 / (double) FRAMES_PER_SECOND);
    // long frameDelayms = 1000;

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
