package com.tom_roush.musclesloth;

import java.util.ArrayList;

/**
 * Class for the workout object. This object will be used to create and edit workouts
 */
class Workout {
    private String _name;
    private ArrayList<Machine> _machines;

    public Workout(String name) {
        _machines = new ArrayList<Machine>();
        _name = name;
    }

    //MODIFIERS

    /**
     * Adds a machine to the given workout
     *
     * @param m com.tom_roush.musclesloth.Machine that needs to be added
     */
    public void addMachineToWorkout(Machine m) {
        _machines.add(m);
    }

    /**
     * Removes the first occurrence of machine m
     *
     * @param m The object of the machine to delete
     */
    public void deleteMachineFromWorkout(Machine m) {
        _machines.remove(m);
    }

    //GETTERS

    /**
     * Returns the number of machines in the workout
     *
     * @return number of machines
     */
    public int numOfMachines() {
        return _machines.size();
    }

    /**
     * The toString override
     * @return the name of the workout
     */
    @Override
    public String toString() {return _name;}

}

