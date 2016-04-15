/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.worlds.vacuum;

import ai.worlds.Location;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author William
 */
public class William1VacuumAgent extends VacuumAgent {

    Set<Model> internalState = new HashSet<Model>();
    List<Location> around = new ArrayList<Location>();

    @Override
    public void determineAction() {
        Location loc = this.body.loc;
        Vector p = (Vector) percept;

        if (around.size() != 4) {

            Location foward = loc.forward(body.heading);
            around.add(foward);
            action = "turn left";
            
        } else {
            for(Location l : around){
                Model m = new Model(l,"unknown");
            }

//            if (internalState.isEmpty()) {
//
//                Model unknow = new Model(this.body.heading.forward(this.body.heading), "unknown");
//            }
//
//            if (p.elementAt(1) == "dirt") {
//                Model m = new Model(l, "clean");
//                internalState.add(m);
//                action = "suck";
//            } else if (p.elementAt(2) == "home") {
//                if (internalState.isEmpty()) {
//                    action = "shut-off";
//
//                } else {
//
//                    action = "forward";
//                }
//
//            } else if (p.elementAt(1) == "dump") {
//
//                Model m1 = new Model(l, "dump");
//                internalState.add(m1);
//                action = "turn left";
//            } else {
//                this.body.heading.forward(this.body.heading);
//            }

        }

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
