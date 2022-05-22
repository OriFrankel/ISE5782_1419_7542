/**
 * 
 */
package primitives;

/**
 * class for the light attributes of a material
 * 
 * @author ori frankel and yair sprecher
 *
 */
public class Material {
	/**
	 * diffuse coefficient
	 */
	public Double3 kD = Double3.ZERO;
	/**
	 * specular coefficient
	 */
	public Double3 kS = Double3.ZERO;
	/**
	 * refraction coefficient
	 */
	public Double3 kT = Double3.ZERO;
	/**
	 * reflection coefficient
	 */
	public Double3 kR = Double3.ZERO;
	/**
	 * the power of the shininess
	 */
	public int nShininess = 0;

	/**
	 * setter for the diffuse coefficient
	 * 
	 * @param kD the diffuse coefficient
	 * @return the resulting Material
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * setter for the diffuse coefficient
	 * 
	 * @param kD the diffuse coefficient
	 * @return the resulting Material
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * setter for the Specular coefficient
	 * 
	 * @param kS the Specular coefficient
	 * @return the resulting Material
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * setter for the Specular coefficient
	 * 
	 * @param kS the Specular coefficient
	 * @return the resulting Material
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * setter for the shininess
	 * 
	 * @param nSh the shininess
	 * @return the resulting Material
	 */
	public Material setShininess(int nSh) {
		nShininess = nSh;
		return this;
	}
	/**
	 * setter for the refraction coefficient
	 * @param d the refraction coefficient
	 * @return the object
	 */
	public Material setKt(double d) {
		kT = new Double3(d);
		return this;
	}
	/**
	 * setter for the reflection coefficient
	 * @param d the reflection coefficient
	 * @return the object
	 */
	public Material setKr(double d) {
		kR = new Double3(d);
		return this;
	}
}
