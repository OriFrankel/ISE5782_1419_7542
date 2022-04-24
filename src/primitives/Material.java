/**
 * 
 */
package primitives;

/**
 * class for the light attributes of a material
 * @author ori frankel and yair sprecher
 *
 */
public class Material {
	/**
	 * diffuse and Specular coefficients
	 */
	public double kD=0,kS=0;
	/**
	 * the power of the shininess
	 */
	public int nShininess=0;
	/**
	 * setter for the diffuse coefficient
	 * @param kD the diffuse coefficient
	 * @return the resulting Material
	 */
	public Material setKd(double kD) {
		this.kD=kD;
		return this;
	}
	/**
	 * setter for the Specular coefficient
	 * @param kS the Specular coefficient
	 * @return the resulting Material
	 */
	public Material setKs(double kS) {
		this.kS=kS;
		return this;
	}
	/**
	 * setter for the shininess
	 * @param nSh the shininess
	 * @return the resulting Material
	 */
	public Material setShininess(int nSh) {
		nShininess=nSh;
		return this;
	}
	
}
