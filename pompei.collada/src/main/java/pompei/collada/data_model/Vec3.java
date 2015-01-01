package pompei.collada.data_model;

public class Vec3 {
  public float x, y, z;
  
  public Vec3() {
    x = 0;
    y = 0;
    z = 0;
  }
  
  public Vec3(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void set(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Vec3(Vec3 vec) {
    if (vec == null) {
      x = 0;
      y = 0;
      z = 0;
    } else {
      x = vec.x;
      y = vec.y;
      z = vec.z;
    }
  }
  
  public Vec3 copy() {
    return new Vec3(this);
  }
  
  public Vec3 plus(Vec3 a) {
    return new Vec3(x + a.x, y + a.y, z + a.z);
  }
  
  public Vec3 add(Vec3 a) {
    x += a.x;
    y += a.y;
    z += a.z;
    return this;
  }
  
  public Vec3 sub(Vec3 a) {
    x -= a.x;
    y -= a.y;
    z -= a.z;
    return this;
  }
  
  public Vec3 minus(Vec3 a) {
    return new Vec3(x - a.x, y - a.y, z - a.z);
  }
  
  public Vec3 div(float a) {
    return new Vec3(x / a, y / a, z / a);
  }
  
  public Vec3 mul(float a) {
    return new Vec3(x * a, y * a, z * a);
  }
  
  public Vec3 divMe(float a) {
    x /= a;
    y /= a;
    z /= a;
    return this;
  }
  
  public Vec3 mulMe(float a) {
    x *= a;
    y *= a;
    z *= a;
    return this;
  }
  
  public float mul(Vec3 b, Vec3 c) {
    return x * b.y * c.z//
        - x * b.z * c.y //
        - y * b.x * c.z //
        + y * b.z * c.x //
        + z * b.x * c.y //
        - z * b.y * c.x;//
  }
  
  public float len() {
    return (float)Math.sqrt(x * x + y * y + z * z);
  }
  
  public Vec3 vmul(Vec3 b) {
    return new Vec3(y * b.z - z * b.y, z * b.x - x * b.z, x * b.y - y * b.x);
  }
}
