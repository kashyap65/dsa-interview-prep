package com.kashyap.dsa.stack;

import java.util.HashMap;

public class LongestSubstringAtMost2Distinct {

    public static void main(String[] args) {
        System.out.print(new LongestSubstringAtMost2Distinct().longestSubstringwithAtmost2chars("aa"));
    }

    public int longestSubstringwithAtmost2chars(String s){
        if(s==null || s.length()==0)
            return 0;

            int maxLen = 0;

            int i=0,j=0;
            int n=s.length();

            HashMap<Character,Integer> charMap = new HashMap<>();

            while(j<n){
                char ch = s.charAt(j);
                charMap.put(ch,charMap.getOrDefault(ch,0)+1);
                if(charMap.size()<=2){
                    maxLen = Math.max(j-i+1,maxLen);
                }
                while(charMap.size()>2){
                    char remove = s.charAt(i);
                    i++;
                    charMap.put(remove,charMap.get(remove)-1);
                    if(charMap.get(remove)==0){
                        charMap.remove(remove);
                    }
                }
                j++;
            }

        return maxLen;
    }
}
