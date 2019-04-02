package com.example.sherisesinyeelam.java4kids.UserProfilePage;

public class LevelUpTable {

    public int levelUp(int totalScore){

        if(totalScore >= 45000){
            return 30;
        } else if(totalScore >= 41000){
            return 29;
        } else if(totalScore >= 38000){
            return 28;
        } else if(totalScore >= 35000){
            return 27;
        } else if(totalScore >= 32000){
            return 26;
        } else if(totalScore >= 29000){
            return 25;
        } else if(totalScore >= 26500){
            return 24;
        } else if(totalScore >= 24000){
            return 23;
        } else if(totalScore >= 21500){
            return 22;
        } else if(totalScore >= 19000){
            return 21;
        } else if(totalScore >= 17000){
            return 20;
        } else if(totalScore >= 15000){
            return 19;
        } else if(totalScore >= 13000){
            return 18;
        } else if(totalScore >= 11000){
            return 17;
        } else if(totalScore >= 10000){
            return 16;
        } else if(totalScore >= 9000){
            return 15;
        } else if(totalScore >= 8000){
            return 14;
        } else if(totalScore >= 7200){
            return 13;
        }  else if(totalScore >= 6400){
            return 12;
        } else if(totalScore >= 5600){
            return 11;
        } else if(totalScore >= 4800){
            return 10;
        } else if(totalScore >= 4000){
            return 9;
        } else if(totalScore >= 3200){
            return 8;
        } else if(totalScore >= 2600){
            return 7;
        } else if(totalScore >= 2000){
            return 6;
        } else if(totalScore >= 1500){
            return 5;
        } else if(totalScore >= 1000){
            return 4;
        } else if(totalScore >= 500){
            return 3;
        } else if(totalScore >= 250){
            return 2;
        }


        return 1;
    }
}
