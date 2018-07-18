public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return Math.sqrt(dx*dx + dy*dy);

	}

	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		return (G*this.mass*p.mass)/(r*r);
	}
	
	public double calcForceExertedByX(Planet p){
		double r = this.calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		return (this.calcForceExertedBy(p)*dx)/r;

	}

	public double calcForceExertedByY(Planet p){
		double r = this.calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		return (this.calcForceExertedBy(p)*dy)/r;

	}

	public double calcNetForceExertedByX(Planet[] planets){
		double netX = 0;
		for (Planet p : planets){
			if (this.equals(p)){
				continue;
			}else{
				netX += this.calcForceExertedByX(p);
			}
		}
		return netX;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double netY = 0;
		for (Planet p : planets){
			if (this.equals(p)){
				continue;
			}else{
				netY += this.calcForceExertedByY(p);
			}
		}
		return netY;
	}

	public void update(double dt, double fX, double fY){
		double xxAcc = fX/this.mass;
		double yyAcc = fY/this.mass;

		this.xxVel += dt * xxAcc;
		this.yyVel += dt * yyAcc;

		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}


























