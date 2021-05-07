import java.awt.Color;

import javax.swing.JFrame;

public class MultiPlayerGF extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MultiPlayerGP mpgp;
	public boolean running = true;
	Thread thread;
	
	MultiPlayerGF(){
		
		JFrame frame = new JFrame();
		mpgp = new MultiPlayerGP();
		
		frame.add(mpgp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.black);
		frame.setTitle("MultiPlayer Snake");
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
				
		//put run loop here
		runLoop rl = new runLoop();
		rl.start();
		
	}
	
	class runLoop implements Runnable {

		public void start() { //creates and starts a new thread
			running = true;
			thread = new Thread(this);
			thread.start();			
		}
		
		public void stop() { //pauses the thread
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
		@Override
		public void run(){ // executes mpgp methods until stop() is executed and running is = false;
			while(running) {
				mpgp.movePlayer();
				mpgp.updateScore();
				mpgp.updateCoin();
				if(mpgp.collisionWithBorder() == true) {
					stop();
				}
				if(mpgp.collisionWithSelf() == true) {
					stop();
				}
				if(mpgp.collisionWithPlayer() == true) {
					stop();
				}
				mpgp.whoIsWinner();
				mpgp.repaint();
				
			}
		}
		
		
	}
}