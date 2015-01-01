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
}
