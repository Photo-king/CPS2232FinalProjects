package projectCPS2232;
import java.util.*;

public class Sheet {
    //data field
      /* this data field no need to add one by one, because
    if you need to use them ,you can use the method
     public static HashMap<Long, Zoo> refreshAndReturnCorrespondingHashMap(int key)
     to refresh corresponding hashMap and return it
     */
    private static HashMap<Long, Zoo> animalSheet;
    private static HashMap<Long, Zoo> staffSheet;
    private static HashMap<Long, Zoo> zooRegionSheet;
    public static final int ZOOREGION_KEY = 0;
    public static final int STAFF_KEY = 1;
    public static final int ANIMAL_KEY = 2;
    public static final int ALL_KEY = 3;

    // Constructors and other class members
    public static LinkedHashMap<Long, Zoo> searchById(long ID, int key) {//zooRegion:0 ,staff:1 animal:2 all:3
        HashMap<Long, Zoo> searchPool = refreshAndReturnCorrespondingHashMap(key);
        Iterator<HashMap.Entry<Long, Zoo>> iterator = searchPool.entrySet().iterator();
        LinkedHashMap<Long, Zoo> result = new LinkedHashMap<>(); // ready for result

        List<Map.Entry<Long, Zoo>> partialMatches = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<Long, Zoo> entry = iterator.next();
            if (entry.getKey().equals(ID)) {//because id is unique, if find, return
                result.put(entry.getKey(), entry.getValue());
                return result;
            } else {
                int lcsLength = longestCommonSubstringLength(String.valueOf(ID), String.valueOf(entry.getKey()));
                if (lcsLength >= 5) {
                    partialMatches.add(entry);
                }
            }
        }

        //because there is no same, so find some similar ones
        // Sort the partial matches based on the longest common substring length
        Collections.sort(partialMatches, new Comparator<Map.Entry<Long, Zoo>>() {
            @Override
            public int compare(Map.Entry<Long, Zoo> o1, Map.Entry<Long, Zoo> o2) {
                int lcsLength1 = longestCommonSubstringLength(String.valueOf(ID), String.valueOf(o1.getKey()));
                int lcsLength2 = longestCommonSubstringLength(String.valueOf(ID), String.valueOf(o2.getKey()));
                return Integer.compare(lcsLength2, lcsLength1); // Sort in descending order
            }

        });

        // Add the sorted entries to the result LinkedHashMap
        for (Map.Entry<Long, Zoo> entry : partialMatches) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }// end of searchById

    public static LinkedHashMap<Long, Zoo> searchByName(String name, int key) {//zooRegion:0 ,staff:1 animal:2 all:3
        HashMap<Long, Zoo> searchPool = refreshAndReturnCorrespondingHashMap(key);
        // a literator come from Zoo, ids: HashMap<Long, Zoo>
        Iterator<HashMap.Entry<Long, Zoo>> iterator = searchPool.entrySet().iterator();
        LinkedHashMap<Long, Zoo> result = new LinkedHashMap<>();

        List<Map.Entry<Long, Zoo>> partialMatches = new ArrayList<>();
        if (name == null)//check whether the nameOfType is null, avoid error
            return result;
        while (iterator.hasNext()) {
            Map.Entry<Long, Zoo> entry = iterator.next();
            String entryName = entry.getValue().getName();//reduce compute twice
            if (entryName.equalsIgnoreCase(name)) {
                result.put(entry.getKey(), entry.getValue());
            } else {
                int lcsLength = longestCommonSubstringLength(name, entryName);
                if (lcsLength >= 5) {
                    partialMatches.add(entry);
                }
            }
        }
        if (result.size() > 0)
            return result;

        // Sort the partial matches based on the longest common substring length
        Collections.sort(partialMatches, new Comparator<Map.Entry<Long, Zoo>>() {
            @Override
            public int compare(Map.Entry<Long, Zoo> o1, Map.Entry<Long, Zoo> o2) {
                int lcsLength1 = longestCommonSubstringLength(name, o1.getValue().getName());
                int lcsLength2 = longestCommonSubstringLength(name, o2.getValue().getName());
                return Integer.compare(lcsLength2, lcsLength1); // Sort in descending order
            }

        });

        // Add the sorted entries to the result LinkedHashMap
        for (Map.Entry<Long, Zoo> entry : partialMatches) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }// end of searchByName

    public static LinkedHashMap<Long, Zoo> searchAnimalBySpecies(String speciesName) {//default key as animal(2)
        HashMap<Long, Zoo> searchPool = refreshAndReturnCorrespondingHashMap(Sheet.ANIMAL_KEY);
        // a literator come from Zoo, ids: HashMap<Long, Zoo>
        Iterator<HashMap.Entry<Long, Zoo>> iterator = searchPool.entrySet().iterator();
        LinkedHashMap<Long, Zoo> result = new LinkedHashMap<>();

        List<Map.Entry<Long, Zoo>> partialMatches = new ArrayList<>();
        if (speciesName == null)//check whether the nameOfType is null, avoid error
            return result;
        while (iterator.hasNext()) {
            Map.Entry<Long, Zoo> entry = iterator.next();
            String entryName = entry.getValue().getType();//reduce compute twice
            if (entryName.equalsIgnoreCase(speciesName)) {
                result.put(entry.getKey(), entry.getValue());
            } else {
                int lcsLength = longestCommonSubstringLength(speciesName, entryName);
                if (lcsLength >= speciesName.length() - 2) {
                    partialMatches.add(entry);
                }
            }
        }
        if (result.size() > 0)
            return result;

        // Sort the partial matches based on the longest common substring length
        Collections.sort(partialMatches, new Comparator<Map.Entry<Long, Zoo>>() {
            @Override
            public int compare(Map.Entry<Long, Zoo> o1, Map.Entry<Long, Zoo> o2) {
                int lcsLength1 = longestCommonSubstringLength(speciesName, o1.getValue().getType());
                int lcsLength2 = longestCommonSubstringLength(speciesName, o2.getValue().getType());
                return Integer.compare(lcsLength2, lcsLength1); // Sort in descending order
            }

        });

        // Add the sorted entries to the result LinkedHashMap
        for (Map.Entry<Long, Zoo> entry : partialMatches) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }// end of searchBySpecies

    // dynamic programing to check the max length the two string same part
    private static int longestCommonSubstringLength(String s1, String s2) {
        // this is a helper method to compute the max length of the continous same part
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLength = 0;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        return maxLength;
    }


    // Method to search staff by phone
    public static Staff searchStaffByPhone(String phone) {
        if(phone == null)
            return null;
        HashMap<Long, Zoo> searchPool = refreshAndReturnCorrespondingHashMap(Sheet.STAFF_KEY);
        for (Map.Entry<Long, Zoo> entry : searchPool.entrySet()) {
            Zoo staff = entry.getValue();
            if (staff instanceof Staff) {
                String phoneNumber = ((Staff)staff).getPhone();
                if(phoneNumber != null && phoneNumber.equals(phone))
                    return (Staff) staff;
            }
        }
        return null;
    }

    //method to refresh animalSheet and staffSheet and ZooRegionSheet and return is
    // for example, if you enter 0, it will only refresh zooRegionSheet
    //zooRegion:0 ,staff:1 animal:2 all:3
    public static HashMap<Long, Zoo> refreshAndReturnCorrespondingHashMap(int refreshKey) {
        animalSheet = new HashMap<>();
        staffSheet = new HashMap<>();
        zooRegionSheet = new HashMap<>();
        if(refreshKey == ALL_KEY)
            return Zoo.getIds();
        for (Map.Entry<Long, Zoo> entry : Zoo.getIds().entrySet()) {
            int key = (int) (entry.getKey() % 10);
            if (key == refreshKey){
                switch (key) {
                    case ZOOREGION_KEY:
                        zooRegionSheet.put(entry.getKey(), entry.getValue());
                        break;
                    case STAFF_KEY:
                        staffSheet.put(entry.getKey(), entry.getValue());
                        break;
                    case ANIMAL_KEY:
                        animalSheet.put(entry.getKey(), entry.getValue());
                        break;
                }
            }
        }
        return refreshKey == ZOOREGION_KEY ? zooRegionSheet : (refreshKey == STAFF_KEY ? staffSheet : animalSheet);
    }
}

