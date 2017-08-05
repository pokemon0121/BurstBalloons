import java.util.*;

public class Solution {

	private static Map<List<Integer>, Integer> dp = new HashMap<>();
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = {8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2,9,6,3,6};
		System.out.println(solution.maxCoins(nums));
		System.out.println(dp.size());
	}

	public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            l.add(nums[i]);
        }
        getMax(l);
        return dp.get(l);
    }
    
    private void getMax(List<Integer> list) {
        int n = list.size();
        int max = Integer.MIN_VALUE;
        if (list.size() == 1) {
            dp.put(list, list.get(0));
            return;
        }
        else if (list.size() == 2) {
            dp.put(list, list.get(0) * list.get(1) + Math.max(list.get(1), list.get(0)));
            return;
        }
        for (int i = 0; i < n; i++) {
            List<Integer> cur = new LinkedList<>(list);
            int x;
            if (i == 0) {
                x = list.get(i) * list.get(i + 1);
            }
            else if (i == list.size() - 1) {
                x = list.get(i) * list.get(i - 1);
            }
            else {
                x = list.get(i) * list.get(i - 1) * list.get(i + 1);
            }
            cur.remove(i);
            if (!dp.containsKey(cur)) {
                getMax(cur);
            }
            max = Math.max(max, x + dp.get(cur));
        }
        dp.put(list, max);
        //System.out.println("max coin of " + list + " is: " + max);
    }
}
