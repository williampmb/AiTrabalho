/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.worlds.vacuum;

import ai.worlds.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author William
 */
public class William1VacuumAgent extends VacuumAgent {

    Location currentGoal;
    Map<Location, String> internalState = new HashMap<Location, String>();
    private boolean goalSetted = false;
    private boolean allClean;
    private boolean arrivedAtGoal = false;
    private boolean firstyThenx = false;
    private Location lastBump;

    @Override
    public void determineAction() {

        Vector p = (Vector) percept;
        Location loc = this.body.loc;
        Location forward = loc.forward(body.heading);

        if (p.elementAt(0) == "bump") {
            internalState.put(forward, "bump");

            arrivedAtGoal = false;
            goalSetted = false;
            firstyThenx = true;
            if(lastBump != null && (lastBump.x == forward.x && lastBump.y == forward.y)){
                if(firstyThenx){
                    firstyThenx = false;
                }else{
                    firstyThenx = true;
                }
            }
            lastBump = new Location(forward.x, forward.y);
            
            action = "turn left";
            return;
        }else if(p.elementAt(1)== "dirt"){
            internalState.put(loc, "clean");
            action = "suck";
            return;
        }
        
        if(allClean){
            action = "shut-off";
            return;
        }

        if (internalState.isEmpty()) {
            internalState.put(loc, "home");
            arrivedAtGoal = true;
        }

        if (arrivedAtGoal) {
            arrivedAtGoal = false;
            goalSetted = false;
            if (p.elementAt(1) == "dirt") {
                //TODO - to analyze if it is home
                internalState.put(loc, "clean");
                action = "suck";

                return;

            } else {
                internalState.put(loc, "clean");
                action = "turn left";
            }
        }

        Location around1 = new Location(loc.x, loc.y);
        around1.x += 1;
        Location around2 = new Location(loc.x, loc.y);
        around2.y += 1;
        Location around3 = new Location(loc.x, loc.y);
        around3.x -= 1;
        Location around4 = new Location(loc.x, loc.y);
        around4.y -= 1;

        if (internalState.get(around1) == null) {
            internalState.put(around1, "unknown");
        }
        if (internalState.get(around2) == null) {
            internalState.put(around2, "unknown");
        }
        if (internalState.get(around3) == null) {
            internalState.put(around3, "unknown");
        }
        if (internalState.get(around4) == null) {
            internalState.put(around4, "unknown");
        }

        if (!goalSetted) {
            for (Location s : internalState.keySet()) {
                String state = internalState.get(s);
                if (state.equals("unknown")) {
                    currentGoal = s;
                    goalSetted = true;
                    allClean = false;
                    break;
                }
                allClean = true;
            }
        }

        // Going to the goal
        if (!arrivedAtGoal) {
            if (!firstyThenx) {
                if (loc.x < currentGoal.x) {
                    if (loc.x >= forward.x) {
                        action = "turn left";
                        return;
                    }
                    if (forward.x <= currentGoal.x) {
                        action = "forward";
                        return;
                    }
                } else if (loc.x > currentGoal.x) {
                    if (loc.x <= forward.x) {
                        action = "turn left";
                        return;
                    }
                    if (forward.x >= currentGoal.x) {
                        action = "forward";
                        return;
                    }
                } else if (loc.y < currentGoal.y) {
                    if (loc.y >= forward.y) {
                        action = "turn left";
                        return;
                    }
                    if (forward.y <= currentGoal.y) {
                        action = "forward";
                        return;
                    }
                } else if (loc.y > currentGoal.y) {
                    if (loc.y < forward.y) {
                        action = "turn left";
                        return;
                    }
                    if (forward.y >= currentGoal.y) {
                        action = "forward";
                        return;
                    }
                } else if (loc.x == currentGoal.x && loc.y == currentGoal.y) {
                    arrivedAtGoal = true;
                } else {
                    System.out.println("some problem");
                }
            } else {
                if (loc.y < currentGoal.y) {
                    if (loc.y >= forward.y) {
                        action = "turn left";
                        return;
                    }
                    if (forward.y <= currentGoal.y) {
                        action = "forward";
                        return;
                    }
                } else if (loc.y > currentGoal.y) {
                    if (loc.y <= forward.y) {
                        action = "turn left";
                        return;
                    }
                    if (forward.y >= currentGoal.y) {
                        action = "forward";
                        return;
                    }
                }
                if (loc.x < currentGoal.x) {
                    if (loc.x >= forward.x) {
                        action = "turn left";
                        return;
                    }
                    if (forward.x <= currentGoal.x) {
                        action = "forward";
                        return;
                    }
                } else if (loc.x > currentGoal.x) {
                    if (loc.x <= forward.x) {
                        action = "turn left";
                        return;
                    }
                    if (forward.x >= currentGoal.x) {
                        action = "forward";
                        return;
                    }
                } else if (loc.x == currentGoal.x && loc.y == currentGoal.y) {
                    arrivedAtGoal = true;
                } else {
                    System.out.println("some problem");
                }
            }

        }

        if (arrivedAtGoal) {
            arrivedAtGoal = false;
            goalSetted = false;
            if (p.elementAt(1) == "dirt") {
                //TODO - to analyze if it is home
                internalState.put(loc, "clean");
                action = "suck";

                return;

            } else {
                internalState.put(loc, "clean");
                action = "turn left";
            }
        }

//        else if (p.elementAt(0) == "bump") {
//                internalState.put(loc, "bump");
//                action = "turn left";
//                return;
//
//            }
//		if (p.elementAt(1) == "dirt") {
//			
//			Model m = new Model(loc, "clean");
//			internalState.add(m);
//			action = "suck";
//			
//		} else if (p.elementAt(2) == "home") {
//			if(allClean){
//				action = "shut-down";
//			}else{
//				if(!lookAround){
//					boolean inside = false;
//					for(Model m : internalState){
//						if(m.l.x == forward.x && m.l.y == forward.y){
//							inside = true;
//						}
//					}
//					
//					if(!inside){
//						Model newModel = new Model(forward, "unknown");
//						internalState.add(newModel);
//					}
//                                        
//					count++;
//                                        
//					if(count == 4){
//						lookAround=true;
//					}
//					action = "turn left";
//					
//				}else{
//					
//					
//				}
//			}
//
//		} else if (p.elementAt(1) == "dump") {
//
//			Model m1 = new Model(loc, "dump");
//			internalState.add(m1);
//			action = "turn left";
//		} else {
//			this.body.heading.forward(this.body.heading);
//		}
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

}
