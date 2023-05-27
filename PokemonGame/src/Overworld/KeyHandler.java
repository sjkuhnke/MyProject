package Overworld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed, pPressed, sPressed, bPressed, wPressed, backslashPressed, dPressed;
	private boolean pause;
	
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
		if (code == KeyEvent.VK_P) {
			pPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			sPressed = true;
		}
		if (code == KeyEvent.VK_B) {
			bPressed = true;
		}
		if (code == KeyEvent.VK_W) {
			wPressed = true;
		}
		if (code == KeyEvent.VK_BACK_SLASH) {
			backslashPressed = true;
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
		if (code == KeyEvent.VK_P) {
			pPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			sPressed = false;
		}
		if (code == KeyEvent.VK_B) {
			bPressed = false;
		}
		if (code == KeyEvent.VK_W) {
			wPressed = false;
		}
		if (code == KeyEvent.VK_BACK_SLASH) {
			backslashPressed = false;
		}
		
	}
	
	public void pause() {
		pause = true;
		downPressed = upPressed = leftPressed = rightPressed = sPressed = wPressed = bPressed = pPressed = backslashPressed = dPressed = false;
	}
	public void resume() {
		pause = false;
	}

}
