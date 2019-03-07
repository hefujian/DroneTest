package com.fujian;
import java.util.ArrayList;
import java.util.List;

/*****
 * Step1: created three classes, including Drone, PackageInfo and Trip.
 *          Drone and Package have one static method in each class, it uses to create the class from the input.
 *          Class trip is used to handler result.
 * Step2: Used MergeSort method to sort packages by their weight.
 * Step3: Calculated the most efficient deliveries for the drone to make on each trip. In this step,
 *          just pick up the package which is lighter than or equal to the maxWeight of drone.
 */
public class Main {
    private static String inputString =
            "Line 1: [Drone #1 Name], [#1 10]\n" +
            "Line 2: [Location #1 Name1], [Location #1 2]\n" +
            "Line 3: [Location #2 Name2], [Location #2 4]\n" +
            "Line 4: [Location #3 Name3], [Location #3 2]\n" +
            "Line 5: [Location #4 Name4], [Location #4 6]\n" +
            "Line 6: [Location #5 Name5], [Location #5 4]\n" +
            "Line 7: [Location #6 Name6], [Location #6 3]\n" +
            "Line 8: [Location #7 Name7], [Location #7 3]\n" +
            "Line 9: [Location #8 Name8], [Location #8 5]\n";


    public static void main(String[] args) {

        Drone drone = Drone.createDroneFromInput(inputString);
        List<PackageInfo> packageInfoList = PackageInfo.createPackageInfoListFromInput(inputString);
        packageInfoList = sortPackageByWeight(packageInfoList, 0, packageInfoList.size() - 1);
        List<Trip> trips = getTripList(drone, packageInfoList);
        printTheResult(drone, trips);
    }

    private static void printTheResult(Drone drone, List<Trip> trips) {
        System.out.printf("[Drone %s %s]%n", drone.getId(), drone.getName());
        for (int i = 0; i < trips.size(); i++) {
            System.out.printf("Trip #%d %n", i + 1);
            for (int j = 0; j < trips.get(i).getPackages().size(); j++) {
                if (j != 0) {
                    System.out.print(", ");
                }
                System.out.printf("[Location %s %s]", trips.get(i).getPackages().get(j).getId(), trips.get(i).getPackages().get(j).getLocation());
            }
            System.out.print("\n");
        }
    }

    private static List<Trip> getTripList(Drone drone, List<PackageInfo> sortedPackageList) {
        List<Trip> tripList = new ArrayList<>();
        int curWeight = 0;
        Trip curTrip = new Trip();

        List<PackageInfo> packageInfoList = new ArrayList<>();
        for (int i = 0; i < sortedPackageList.size(); i++) {

            if (sortedPackageList.get(i).getPackageWeight() <= drone.getMaxWeight()) { // just pick up the package which is lighter than or equal to the maxWeight of drone.

                curWeight += sortedPackageList.get(i).getPackageWeight();
                if (curWeight <= drone.getMaxWeight()) {
                    packageInfoList.add(sortedPackageList.get(i));
                } else {
                    curTrip.setPackages(packageInfoList); //create one trip
                    tripList.add(curTrip);
                    curWeight = sortedPackageList.get(i).getPackageWeight();

                    packageInfoList = new ArrayList<>();
                    packageInfoList.add(sortedPackageList.get(i));
                    curTrip = new Trip();
                }
            }
            if (i == sortedPackageList.size() - 1 && packageInfoList.size()>0) { // last package
                curTrip.setPackages(packageInfoList);
                tripList.add(curTrip);
            }
        }

        return tripList;
    }

    /***
     * Sort package information by weight.
     * @param list
     * @param low
     * @param high
     * @return
     */
    private static List<PackageInfo> sortPackageByWeight(List<PackageInfo> list, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sortPackageByWeight(list, low, mid);
            sortPackageByWeight(list, mid + 1, high);
            merge(list, low, mid, high);
        }
        return list;
    }

    public static void merge(List<PackageInfo> list, int low, int mid, int high) {
        List<PackageInfo> temp = new ArrayList<>(high - low + 1);

        for (int i = 0; i < high - low + 1; i++) {
            temp.add(new PackageInfo());
        }

        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {
            if (list.get(i).getPackageWeight() < list.get(j).getPackageWeight()) {
                temp.set(k++, list.get(i++));
            } else {
                temp.set(k++, list.get(j++));
            }
        }

        while (i <= mid) {
            temp.set(k++, list.get(i++));
        }
        while (j <= high) {
            temp.set(k++, list.get(j++));
        }
        for (int k2 = 0; k2 < temp.size(); k2++) {
            list.set(k2 + low, temp.get(k2));
        }
    }
}
