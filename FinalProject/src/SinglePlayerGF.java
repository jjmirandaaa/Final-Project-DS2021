import java.awt.Color;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


public class SinglePlayerGF extends JFrame {
	
	private SinglePlayerGP spgp;
	public boolean running;
	Thread thread;
	  
	SinglePlayerGF(){
		
		JFrame frame = new JFrame();
		spgp = new SinglePlayerGP();
		
		frame.add(spgp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.black);
		frame.setTitle("SinglePlayer Snake");
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		runLoop rl = new runLoop();
		rl.start();
	}
	
	class runLoop implements Runnable {
		
		public void start() {
			running = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	
		public void stop() {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while(running) {
				spgp.movePlayer();
				spgp.updateScore();
				spgp.updateCoin();
				if(spgp.collisionWithBorder() == true) {
					stop();
				}
				if(spgp.collisionWithSelf() == true) {
					stop();
				}
				//System.out.println());
				spgp.repaint();
			}
		}
	}

}
