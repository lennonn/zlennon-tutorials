/**
 * 
 */
package com.zl.instances.designpattern.factory.abstractfactory;

/**
 * @author zlennon
 *
 */
public class AirBWM525LI implements AriCondition {
	public AirBWM525LI(){
		createAriCondition();
	}
	/* (non-Javadoc)
	 * @see com.zl.instances.designpattern.factory.abstractfactory.AriCondition#createAriCondition()
	 */
	@Override
	public void createAriCondition() {
		System.out.println("create ari-conditon bwm525li");
	}

}
