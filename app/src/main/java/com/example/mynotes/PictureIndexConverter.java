package com.example.mynotes;

import java.util.Random;

public class PictureIndexConverter {
    private static Random random = new Random();
    private static Object syncObject = new Object();

    private static int[] picIndex = {
            R.drawable.lynch,
            R.drawable.tarantino,
            R.drawable.woody_allen,
            R.drawable.andrei_tarkovsky,
            R.drawable.jim_jarmusch,
            R.drawable.coppolla,
            R.drawable.fellinni,
            R.drawable.stanley_kubrick,
            R.drawable.kar_wai,
            R.drawable.ic_avatar_foreground
    };

    public static int randomPictureIndex(){
        synchronized (syncObject){
            return random.nextInt(picIndex.length);
        }
    }

    public static int getPictureByIndex(int index){
        if (index < 0 || index >= picIndex.length){
            index = 0;
        }
        return picIndex[index];
    }

    public static int getIndexByPicture(int picture){
        for(int i = 0; i < picIndex.length; i++){
            if (picIndex[i] == picture){
                return i;
            }
        }
        return 0;
    }
}
