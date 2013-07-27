import java.util.*;

class Planet{
 static final double G=100;//power of gravity. increase this for more pull between objects
 static double maxPlanetSpeed=20;//fastest a planet can travel
 static double timeStep=1;//time step of simulation. lower this to increase accuracy
 
 public boolean moveable=true;//
 public double xpos=0,ypos=0;//position of planet on screen
 public double xvel=0,yvel=0;//velocity of planet (pixels/frame)
 public double xaccel=0,yaccel=0;//acceleration of planet(pixels/frame^2)
 public double mass=10;//mass of the planet
 
 public static void setTimeStep(double step){
   timeStep=step;
 }
 
 Planet(boolean rndVel){//creates a new planet, with random velocity if rndVel is true, or zero velocity if rndVel is false
  Random rnd=new Random();
  xpos=rnd.nextDouble()*500+250;
  ypos=rnd.nextDouble()*500+250;
  if(rndVel){
   xvel=rnd.nextDouble()*10-5;
   yvel=rnd.nextDouble()*10-5;
  }
 }

 Planet(double xp,double yp){
  xpos=xp;
  ypos=yp;
 }

 Planet(double xp,double yp,boolean rndVel){
  Random rnd=new Random();
  xpos=xp;
  ypos=yp;
  if(rndVel){
   xvel=rnd.nextDouble()*10-5;
   yvel=rnd.nextDouble()*10-5;
  }
 }

 Planet(double xp,double yp,double m,boolean move){
  xpos=xp;
  ypos=yp;
  mass=m;
  moveable=move;
 }

 Planet(double xp,double yp,double xv,double yv){
  xpos=xp;
  ypos=yp;
  xvel=xv;
  yvel=yv;
 }

 Planet(double xp,double yp,double xv,double yv,double xa,double ya,double m){
  xpos=xp;
  ypos=yp;
  xvel=xv;
  yvel=yv;
  xaccel=xa;
  yaccel=ya;
  mass=m;
 }

 public void setVel(double x,double y){
  xvel=x;
  yvel=y;
 }

 public String toString(){
  String result="Planet. X: "+xpos+" Y: "+ypos;
  return result;
 }

 public void calcPull(Planet[] planets,boolean[] exists){//calculate the acceleration of the planet from an array of planets and an array of if an individual planet exists
  xaccel=0;
  yaccel=0;
  double xa=0,ya=0;
  double mag=0;
  double factor=0;
  double dx=0,dy=0;
  for(int a=0;a<planets.length;a++){
   if(exists[a]&&planets[a]!=null){
    if(planets[a].xpos!=xpos&&planets[a].ypos!=ypos){
     dx=planets[a].xpos-xpos;
     dy=planets[a].ypos-ypos;
     mag=Math.sqrt(dx*dx+dy*dy);//dist between planets
     factor = G*(planets[a].mass*mass)/(mag*mag)/mass;//pull of gravity between planets
     xa+=factor*dx/mag;
     ya+=factor*dy/mag;
     //System.out.println("xpos: "+planets[a].xpos+" "+xpos);
     //System.out.println("ypos: "+planets[a].ypos+" "+ypos);
     //System.out.printf("\ndx:%f\ndy:%f\nmag:%f\nfactor:%f\nxa:%f\nya:%f\n",dx,dy,mag,factor,xa,ya);
    }
   }
  }
  xaccel+=xa;
  yaccel+=ya;
 }

 public void move(){
  if(moveable){
   xvel+=xaccel;
   yvel+=yaccel;
   double speed=Math.sqrt(xvel*xvel+yvel*yvel);
   if(speed>maxPlanetSpeed){//clamp velocity to maximum of v.maxPlanetSpeed
    xvel=xvel/speed*maxPlanetSpeed;
    yvel=yvel/speed*maxPlanetSpeed;
   }
   xpos+=xvel*timeStep;
   ypos+=yvel*timeStep;
  }
 }


}