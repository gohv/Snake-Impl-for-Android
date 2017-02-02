package georgyhristov.xyz.mrnom.framework.math;

import android.util.FloatMath;

/**
 * Created by gohv on 31.01.17.
 */
public class Vector2 {

    public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
    public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
    public float x,y;

    public Vector2() {
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    /*The cpy() method will create a duplicate instance of the current vector and return it. This might
come in handy if we want to manipulate a copy of a vector, preserving the value of the original
vector*/
    public Vector2 cpy() {
        return new Vector2(x,y);
    }

    public Vector2 set(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector2 set(Vector2 other){
        this.x = other.x;
        this.y = other.y;
        return this;
    }

    public Vector2 add(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }
    public Vector2 add(Vector2 other){
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2 sub(float x, float y){
        this.x -= x;
        this.y -= y;
        return this;
    }
    public Vector2 sub(Vector2 other){
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    /*The mul() method simply multiplies the x and y components of the vector with the given scalar
value, and it returns a reference to the vector itself, for chaining.*/
    public Vector2 mul(float scalar){
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }
    /*The len() method calculates the length of the vector exactly, as defined previously
        ORIGINALLY FLOAT !
    */
    public double len(){
        return  Math.sqrt(x * x + y * y);
    }

    /*The nor() method normalizes the vector to unit length. We use the len() method internally
    to first calculate the length. If it is zero, we can bail out early and avoid a division by zero.*/
    public Vector2 not(){
        double len = len();
        if(len != 0){
            this.x /= len;
            this.y /= len;
        }
        return this;
    }

    /*The angle() method calculates the angle between the vector and the x axis using the atan2()
    method, as discussed previously. We have to use the Math.atan2() method, as the FloatMath
    class doesn’t have this method. The returned angle is given in radians, so we convert it to
    degrees by multiplying it by TO_DEGREES. If the angle is less than zero, we add 360˚ to it so that
    we can return a value in the range 0 to 360˚.*/

    public float angle(){
        float angle = (float) Math.atan2(y,x) * TO_DEGREES;
        if(angle < 0)
            angle += 360;

        return angle;
    }

    /*The rotate() method simply rotates the vector around the origin by the given angle. Since the
    FloatMath.cos() and FloatMath.sin() methods expect the angle to be given in radians, we
    first convert them from degrees to radians. Next, we use the previously defined equations to
    calculate the new x and y components of the vector, and then return the vector itself, again for
    chaining.*/

    public Vector2 rotate(float angle) {
        float rad = angle * TO_RADIANS;
        float cos = (float) Math.cos(rad);
        float sin = (float) Math.sin(rad);
        float newX = this.x * cos - this.y * sin;
        float newY = this.x * sin + this.y * cos;
        this.x = newX;
        this.y = newY;
        return this;
    }

    public float dist(Vector2 other) {
        float distX = this.x - other.x;
        float distY = this.y - other.y;
        return (float) Math.sqrt(distX * distX + distY * distY);
    }
    public float dist(float x, float y) {
        float distX = this.x - x;
        float distY = this.y - y;
        return (float) Math.sqrt(distX * distX + distY * distY);
    }
}








