public class NBody{

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (int i=0; i < planets.length; i++){
			planets[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		double time=0;
		while(time != T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i=0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i=0; i < planets.length; i++){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i=0; i < planets.length; i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			time += dt;

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}



	}
	
	public static double readRadius(String fileName){
		In file = new In(fileName);

		int planets = file.readInt();
		double radius = file.readDouble();

		return radius;

	}

	public static Planet[] readPlanets(String fileName){
		In file = new In(fileName);

		int planetNo = file.readInt();
		double radius = file.readDouble();

		Planet[] planets = new Planet[planetNo];

		for (int i=0; i < planets.length; i++){
			planets[i] = new Planet(file.readDouble(),file.readDouble(),file.readDouble(),file.readDouble(),file.readDouble(), file.readString());
		}

		return planets;

	}
}