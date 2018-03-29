package apoio;

import java.util.Random;

/** Generate 10 random integers in the range 0..99. */
public final class RandomInteger {

	public static void main(String[] args) {
		log("Generating 10 random integers in range 0..99.");

		// note a single Random object is reused here
		Random randomGenerator = new Random();
		for (int idx = 1; idx <= 10; ++idx) {
			int randomInt = randomGenerator.nextInt(100);
			log("Generated : " + randomInt);
		}

		log("Done.");
		numAleatorio(700,120);
	}

	private static void log(String aMessage) {
		System.out.println(aMessage);
	}

	public static double numAleatorio(int maximo, int minimo) {
		
		double random = 0;
		
		//Random randomGenerator = new Random();
		for (int i = 0; i < 5; i++) {
			//random = randomGenerator.nextInt((maximo - minimo) + 1) + minimo;
			random = minimo + (int)(Math.random() * ((maximo - minimo) + 1));
			System.out.println(random);
		}
		
		return random;

	}
}
