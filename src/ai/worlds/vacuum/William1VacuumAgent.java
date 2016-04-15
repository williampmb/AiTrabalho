/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.worlds.vacuum;

import ai.worlds.Location;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author William
 */
public class William1VacuumAgent extends VacuumAgent {

	Set<Model> internalState = new LinkedHashSet<Model>();
	List<Location> around = new ArrayList<Location>();
	boolean allClean = false;
	boolean lookAround = false;
	int count =0;

	@Override
	public void determineAction() {
		Location loc = this.body.loc;
		Vector p = (Vector) percept;

		Location forward = loc.forward(body.heading);

		if (p.elementAt(1) == "dirt") {
			
			Model m = new Model(loc, "clean");
			internalState.add(m);
			action = "suck";
			
		} else if (p.elementAt(2) == "home") {
			if(allClean){
				action = "shut-down";
			}else{
				if(!lookAround){
					boolean inside = false;
					for(Model m : internalState){
						if(m.l.x == forward.x && m.l.y == forward.y){
							inside = true;
						}
					}
					
					if(!inside){
						Model newModel = new Model(forward, "unknown");
						internalState.add(newModel);
					}else{
						count++;
					}
					
					if(count == 4){
						lookAround=true;
					}
					action = "turn left";
					
				}else{
					
					
				}
			}

		} else if (p.elementAt(1) == "dump") {

			Model m1 = new Model(loc, "dump");
			internalState.add(m1);
			action = "turn left";
		} else {
			this.body.heading.forward(this.body.heading);
		}

		// for(Location l : around){
		// Model m = new Model(l,"unknown");
		// }

		// if (internalState.isEmpty()) {
		//
		// Model unknow = new
		// Model(this.body.heading.forward(this.body.heading), "unknown");
		// }
		//
		// if (p.elementAt(1) == "dirt") {
		// Model m = new Model(l, "clean");
		// internalState.add(m);
		// action = "suck";
		// } else if (p.elementAt(2) == "home") {
		// if (internalState.isEmpty()) {
		// action = "shut-off";
		//
		// } else {
		//
		// action = "forward";
		// }
		//
		// } else if (p.elementAt(1) == "dump") {
		//
		// Model m1 = new Model(l, "dump");
		// internalState.add(m1);
		// action = "turn left";
		// } else {
		// this.body.heading.forward(this.body.heading);
		// }

	}

	class Model {

		Location l;
		String state;

		Model(Location l, String state) {
			this.l = l;
			this.state = state;
		}

	}

}
