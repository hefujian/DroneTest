package com.fujian;

public class Drone {
    private String id;
    private String name;
    private int maxWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Drone() {
    }

    public Drone(String id, String name, int maxWeight) {
        this.id = id;
        this.name = name;
        this.maxWeight = maxWeight;
    }

    /***
     * Create Drone from Input
     * @param inputString
     * @return
     */
    public static Drone createDroneFromInput(String inputString){
        String input = inputString.split("\n")[0];
        input = input.substring("Line 1: ".length());
        String[] stringDrone = input.split(",");
        String idAndName = stringDrone[0].substring(1,stringDrone[0].length()-1);
        String weight=  stringDrone[1].trim().substring(1,stringDrone[1].trim().length()-1);
        Drone drone = new Drone(idAndName.split(" ")[1],
                idAndName.split(" ")[2],
                Integer.parseInt(weight.split(" ")[1]));

        return drone;
    }
}
