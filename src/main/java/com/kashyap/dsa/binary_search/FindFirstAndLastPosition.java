package com.kashyap.dsa.binary_search;

public class FindFirstAndLastPosition {

    public static void main(String[] args) {
        int[] result = new FindFirstAndLastPosition().findFirstAndLastOccurenceOfTarget(new int[]{1},1);
        System.out.println(result[0]+","+result[1]);
    }

    public int[] findFirstAndLastOccurenceOfTarget(int[] nums, int target) {
        int[] result = {-1, -1};

        if (nums == null || nums.length == 0)
            return result;

        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        result[0] = left == right && nums[left] == target ? left : -1;

        left=0;
        right= nums.length-1;
        while(left<right){
            int mid = left + (right-left+1)/2;

            if(target<nums[mid]){
                right=mid-1;
            }else{
                left=mid;
            }
        }
        result[1] = left == right && nums[left] == target ? left : -1;
        return result;
    }
}

