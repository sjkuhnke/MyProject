package Overworld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, sPressed, wPressed, dPressed;
	boolean pause;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (pause) return;
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			dPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			sPressed = true;
		}
		if (code == KeyEvent.VK_W) {
			wPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			dPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			sPressed = false;
		}
		if (code == KeyEvent.VK_W) {
			wPressed = false;
		}
	}
	
	public void pause() {
		pause = true;
		downPressed = upPressed = leftPressed = rightPressed = sPressed = wPressed = dPressed = false;
	}
	public void resume() {
		pause = false;
	}

}
