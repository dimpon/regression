package com.dimpon.regression;
import java.util.function.Function;

public class Start {

	static double[][] sample = {
			{0.17, 355},
			{0.16, 328},
			{0.17, 350},
			{0.18, 325},
			{0.25, 642},
			{0.16, 342},
			{0.15, 322},
			{0.19, 485},
			{0.21, 483},
			{0.15, 323},
			{0.18, 462},
			{0.28, 823},
			{0.16, 336},
			{0.20, 498},
			{0.23, 595},
			{0.29, 860},
			{0.12, 223},
			{0.26, 663},
			{0.25, 750},
			{0.27, 720},
			{0.18, 468},
			{0.16, 345},
			{0.17, 352},
			{0.16, 332},
			{0.17, 353},
			{0.18, 438},
			{0.17, 318},
			{0.18, 419},
			{0.17, 346},
			{0.15, 315},
			{0.17, 350},
			{0.32, 918},
			{0.32, 919},
			{0.15, 298},
			{0.16, 339},
			{0.16, 338},
			{0.23, 595},
			{0.23, 553},
			{0.17, 345},
			{0.33, 945},
			{0.25, 655},
			{0.35, 1086},
			{0.18, 443},
			{0.25, 678},
			{0.25, 675},
			{0.15, 287},
			{0.26, 693},
			{0.15, 316}
	};

	private static Function<Double, Double> function;

	public static void main(String[] args) {


		double[] allX = allX(sample);
		double[] allY = allY(sample);
		double avgX = avg(allX);
		double avgY = avg(allY);

		double b1 = SSxy(allX, allY, avgX, avgY) / SSx(allX, avgX);
		double b0 = avgY - b1 * avgX;

		System.out.println();
		System.out.println("b1 = " + b1);
		System.out.println("b0 = " + b0);

		function = new LinearFunction(b0, b1);

		System.out.println();
		System.out.println("Function: "+function.toString());
		System.out.println();

		System.out.println("var(x) = " + varX(allX));

		System.out.println("cov(x,y) = " + cov(allX, allY));

		System.out.println("SSE = " + SSE(allX, allY));
		System.out.println("SSR = " + SSR(allX, allY));
		System.out.println("SST = " + SST(allY));
		System.out.println("R*R = " + SSM(allX,allY)/SST(allY));

	}

	static double SSE(double[] allX, double[] allY) {
		double sum = 0;
		for (int i = 0; i < allX.length; i++) {
			sum = sum + Math.pow(allY[i] - function.apply(allX[i]), 2);
		}
		return sum;
	}

	static double SSR(double[] allX, double[] allY) {
		double sum = 0;
		double avgY = avg(allY);
		for (int i = 0; i < allX.length; i++) {
			sum = sum + Math.pow(avgY - function.apply(allX[i]), 2);
		}
		return sum;
	}

	static double SST(double[] allY) {
		double sum = 0;
		double avgY = avg(allY);
		for (int i = 0; i < allY.length; i++) {
			sum = sum + Math.pow((avgY - allY[i]), 2);
		}
		return sum;
	}

	static double SSM(double[] allX, double[] allY) {
		double sum = 0;
		double avgY = avg(allY);
		for (int i = 0; i < allY.length; i++) {
			sum = sum + Math.pow((avgY - function.apply(allX[i])), 2);
		}
		return sum;
	}

	static double SSxy(double[] allX, double[] allY, double avgX, double avgY) {
		double sum = 0;
		for (int i = 0; i < allX.length; i++) {
			sum = sum + (allX[i] - avgX) * (allY[i] - avgY);
		}
		return sum;
	}

	static double cov(double[] allX, double[] allY) {
		double sum = 0;
		double avgX = avg(allX);
		double avgY = avg(allY);
		for (int i = 0; i < allX.length; i++) {
			sum = sum + (allX[i] * allY[i]);
		}

		return sum / allX.length - (avgX * avgY);
	}

	static double varX(double[] allX) {
		double sum = 0;
		for (double x : allX) {
			sum = sum + Math.pow(x, 2);
		}
		return sum / allX.length - Math.pow(avg(allX), 2);
	}

	static double SSx(double[] allX, double avgX) {
		double sum = 0;
		for (double x : allX) {
			sum = sum + Math.pow(x - avgX, 2);
		}
		return sum;
	}

	static double[] allX(double[][] values) {
		double[] rez = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			rez[i] = values[i][0];
		}
		return rez;
	}

	static double[] allY(double[][] values) {
		double[] rez = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			rez[i] = values[i][1];
		}
		return rez;
	}

	static double avg(double[] values) {
		double sum = 0;
		for (double v : values) {
			sum = sum + v;
		}
		return sum / values.length;
	}

	public static class LinearFunction implements Function<Double, Double> {
		private final double b1;
		private final double b0;

		public LinearFunction(double b0, double b1) {
			this.b1 = b1;
			this.b0 = b0;
		}

		@Override
		public Double apply(Double x) {
			return b1 * x + b0;
		}

		@Override
		public String toString() {
			return "y = " + b1 + " * x + " + b0;
		}
	}

/*

0.17, 355,
0.16, 328,
0.17, 350,
0.18, 325,
0.25, 642,
0.16, 342,
0.15, 322,
0.19, 485,
0.21, 483,
0.15, 323,
0.18, 462,
0.28, 823,
0.16, 336,
0.20, 498,
0.23, 595,
0.29, 860,
0.12, 223,
0.26, 663,
0.25, 750,
0.27, 720,
0.18, 468,
0.16, 345,
0.17, 352,
0.16, 332,
0.17, 353,
0.18, 438,
0.17, 318,
0.18, 419,
0.17, 346,
0.15, 315,
0.17, 350,
0.32, 918,
0.32, 919,
0.15, 298,
0.16, 339,
0.16, 338,
0.23, 595,
0.23, 553,
0.17, 345,
0.33, 945,
0.25, 655,
0.35, 1086,
0.18, 443,
0.25, 678,
0.25, 675,
0.15, 287,
0.26, 693,
0.15, 316
*/
}
