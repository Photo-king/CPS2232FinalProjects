package projectCPS2232;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Staff staff = new Staff("Xiaoming");
        Staff staff1 = new Staff("LiHua");
        Animal animal = new Animal("WangCai", "Dog");
        Animal animal1 = new Animal("MiaoMiao", "Cat");
//
        ZooRegion zooRegion = ZooRegion.createNewZooRegion("龙狮城");
        ZooRegion zooRegion1 = ZooRegion.createNewZooRegion("创乐园");
        staff.addKeepAnimals(animal);
        staff.addKeepAnimals(animal1);
        staff1.addKeepAnimals(animal);
//
        zooRegion.addAnimal(animal);
        zooRegion.addAnimal(animal1);
        zooRegion1.addAnimal(animal);
//
//        zooRegion.removeAnimal(animal);
//
//        animal.setZooRegion(zooRegion);
//        animal.setKeeper(staff1);

//        Staff sta1= new Staff("张三");System.out.println(sta1);
//
//        Staff sta2= new Staff("李四");
//        System.out.println(sta2);
//        Staff sta3= new Staff("王五");
//        System.out.println(sta3);
//
//        Animal an1 = new Animal("miao","cat",sta1);
//        System.out.println(an1);
//        HashSet<String> sss = new HashSet<>();
//        sss.add("SSS");
//        sss.add("SS");
//        sss.add("S");
//        System.out.println(sss);

//        String s1 = "12345678920";
//        String s2 = "1234w678921";
//        System.out.println(longestCommonSubstringLength(s1,s2));
        System.out.println("hello");
        HashMap<Long, Zoo> temp = Sheet.refreshAndReturnCorrespondingHashMap(Sheet.STAFF_KEY);
//        System.out.println(temp);
        for (Map.Entry<Long, Zoo> entry : temp.entrySet()) {
            System.out.print(entry.getValue());
        }
    }
    private static  int longestCommonSubstringLength(String s1, String s2) {
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
}
