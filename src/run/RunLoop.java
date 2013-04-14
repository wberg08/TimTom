package run;

import gui.GameDataProvider;
import gui.GameFactory;
import gui.GameWindow;
import gui.impl.GameFactoryImpl;
import gui.impl.test.TestGameDataProvider;

import java.util.HashMap;
import java.util.Map;

import logic.GameFrame;

public class RunLoop {

  private static GameFactory gameFactory;
  private static GameDataProvider gameDataProvider;

  public static void main(String... args) {
    Map<String, String> parameters = parseArgs(args);

    GameFrame gameFrame = new GameFrame();
    if (parameters.containsKey("testmode")) {
      gameFactory = new GameFactoryImpl();
      gameDataProvider = new TestGameDataProvider();
      gameDataProvider.populateGameFrame(gameFrame);
    }
    else {
      return;
    }

    GameWindow gameWindow = gameFactory.getGui(gameFrame, gameDataProvider);

    /*
     * Game properties
     */

    int gameFramesPerSecond = gameDataProvider.getFramesPerSecond();

    /*
     * Choose properties load origin
     */

    //

    /*
     * Calculate derived properties
     */

    long frameDelayms = (long) ((double) 1000 / (double) gameFramesPerSecond);

    while (true) {
      gameWindow.repaint();
      gameFrame.step();

      try {
        Thread.sleep(frameDelayms);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @return a stringly-typed map of options, or null if the game should not
   *         start
   */
  private static Map<String, String> parseArgs(String... args) {
    int i = 0;
    Map<String, String> ret = new HashMap<>();

    while (i < args.length) {
      if (args[i].equals("-h")) {
        System.out.println("Add -t for test mode");
        System.out.println("-i dirpath for your file game data");
        return null;
      }
      else if (args[i].equals("-t")) {
        ret.put("testmode", "true");
        i++;
      }
      else if (args[i].equals("-i")) {
        ret.put("filemode", "true");
        ret.put("gameFileDataPath", args[i + 1]);
        i += 2;
      }
    }

    return ret;
  }

}
