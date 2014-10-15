package pompei.collada.data_model;

public class Matrix {
  public float m00 = 1, m01 = 0, m02 = 0, m03 = 0;
  public float m10 = 0, m11 = 1, m12 = 0, m13 = 0;
  public float m20 = 0, m21 = 0, m22 = 1, m23 = 0;
  public float m30 = 0, m31 = 0, m32 = 0, m33 = 1;
  
  public void setIdentity() {
    //@formatter:off
    m00 = 1; m01 = 0; m02 = 0; m03 = 0;
    m10 = 0; m11 = 1; m12 = 0; m13 = 0;
    m20 = 0; m21 = 0; m22 = 1; m23 = 0;
    m30 = 0; m31 = 0; m32 = 0; m33 = 1;
    //@formatter:on
  }
  
  public void appendDataTo(StringBuilder sb) {
    sb.append(m00).append(' ').append(m01).append(' ').append(m02).append(' ').append(m03)//
        .append(' ')//
        .append(m10).append(' ').append(m11).append(' ').append(m12).append(' ').append(m13)//
        .append(' ')//
        .append(m20).append(' ').append(m21).append(' ').append(m22).append(' ').append(m23)//
        .append(' ')//
        .append(m30).append(' ').append(m31).append(' ').append(m32).append(' ').append(m33);
  }
}
