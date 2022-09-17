package org.coloride.twoodee;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.coloride.twoodee.Game;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//config.setForegroundFPS(60);
		config.setWindowedMode(1280,720);
		//config.setInitialVisible(true);
		config.useVsync(false);
		config.setTitle("Twoodee");
		new Lwjgl3Application(new Game(), config);
	}
}
