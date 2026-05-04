package Test;

import java.util.Random;

public class text {
    static void main(String[] args) {
        int[] tmpArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        for (int i=0;i<tmpArr.length;i++){
            int index = r.nextInt(tmpArr.length);
            int tmp = tmpArr[index];
            tmpArr[index] = tmpArr[i];
            tmpArr[i] = tmp;
        }
        int[][] data = new int[4][4];
        for (int i=0;i<tmpArr.length;i++){
            data[i/4][i%4] = tmpArr[i];
        }
    }





}
