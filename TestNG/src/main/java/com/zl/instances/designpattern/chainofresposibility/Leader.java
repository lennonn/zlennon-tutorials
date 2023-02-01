/**
 * 
 */
package com.zl.instances.designpattern.chainofresposibility;

/**
 * @author zlennon
 *
 */
public abstract class Leader {
	 protected  String name;
	 protected Leader nextLeader;
	 public Leader getNextLeader() {
		return nextLeader;
	}
	public void setNextLeader(Leader nextLeader) {
		this.nextLeader = nextLeader;
	}
	public Leader(String name){
		 this.name =name;
	 }
	public abstract  void handleRequest(LeaveRequest    request);
		
}
