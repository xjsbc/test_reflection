package com.bc;

public class KMP {

    private char[] fatherChar;

    private char[] sonChar;

    public KMP(String fatherStr, String sonStr){
        this.fatherChar = fatherStr.toCharArray();
        this.sonChar = sonStr.toCharArray();
    }

    public boolean isContain(){
        int[] next = getNext();
        // i 指向字符串
        int i = 0;
        // j 指向子串
        int j = 0;
        while (i < fatherChar.length && j < fatherChar.length){
            if(j == -1 || fatherChar[i] == fatherChar[j]){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        return j == fatherChar.length;
    }

    public int[] getNext(){
        int[] next = new int[sonChar.length];
        // -1 是 i 回溯结束的标志，i 从 0 继续开始匹配即可
        next[0] = -1;
        // i 指向前缀，赋值为 -1 、j 指向后缀，赋值为 1 ；表示：0 ~ 1 位没有前后缀，结束即可
        int i = -1;
        int j = 0;
        while (j < sonChar.length - 1){
            if(i == -1 || sonChar[i] == sonChar[j]){
                next[++j] = ++i;
            }else{
                i = next[i];
            }
        }
        return next;
    }
}
