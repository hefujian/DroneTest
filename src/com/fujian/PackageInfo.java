package com.fujian;

import java.util.ArrayList;
import java.util.List;

public class PackageInfo {
    private String id;
    private String location;
    private int packageWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }

    public PackageInfo(String id, String location, int packageWeight) {
        this.id = id;
        this.location = location;
        this.packageWeight = packageWeight;
    }

    public PackageInfo() {
    }

    public static List<PackageInfo> createPackageInfoListFromInput(String inputString){
        List<PackageInfo> list = new ArrayList<PackageInfo>();

        String[] lines = inputString.split("\n");
        for(int i=1;i<lines.length;i++){
            String[] items = lines[i].substring("Line 2: ".length()).split(", "); // remove "Line 2: ", and split it by ", "
            String idAndName = items[0].trim().substring(1,items[0].trim().length()-1);
            String weight = items[1].trim().substring(1,items[1].trim().length()-1).split(" ")[2];
            PackageInfo packageInfo = new PackageInfo(idAndName.split(" ")[1],idAndName.split(" ")[2],Integer.parseInt(weight));
            list.add(packageInfo);
        }
        return list;

    }
}
