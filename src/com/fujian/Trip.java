package com.fujian;

import java.util.List;

public class Trip {
    private List<PackageInfo> packages;


    public List<PackageInfo> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageInfo> packages) {
        this.packages = packages;
    }

    public Trip() {
    }

    public Trip(List<PackageInfo> packages) {
        this.packages = packages;
    }
}
