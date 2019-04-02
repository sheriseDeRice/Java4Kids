package com.example.sherisesinyeelam.java4kids.GamesPage.ChoosingGame;

import com.example.sherisesinyeelam.java4kids.R;

import java.util.HashMap;
import java.util.Map;

public class Lesson1_Inherirtance {

    public int lesson_icon(){
        return R.drawable.inheritance_icon;
    }

    public String lesson_name(){
        return "Inheritance";
    }

    public String lesson_description(){
        String description =
                "- <b>Inheritance</b> is all about the <b>'is-a'</b> relationship." +
                        "<br/><br/>- For example, we have <i><u>Oranges</u></i>." +
                        "<br/><br/>- <i><u>Oranges</u></i> <b>is a</b> kind of <i><u>Fruit</u></i>." +
                        "<br/><br/>- This is what we call <b>inheritance</b> in java programming." +
                        "<br/><br/>- We called <i><u>Oranges</u></i> as <b>Subclass</b> and <i><u>Fruit</u></i> as <b>Superclass</b>." +
                        "<br/><br/>- But <b>Subclass</b> does not have to be <i><u>Oranges</u></i> " +
                        "and <b>Superclass</b> does not have to be <I><u>Fruit</u></i> as well!" +
                        "<br/><br/>- <b>Subclasses</b> for <i><u>Fruit</u></i> can also be like Apples, Bananas and Strawberries..." +
                        "<br/><br/>- It is all just about the <b>Subclass</b>(Child) is a kind of its <b>Superclass</b>(Parent)." +
                        "<br/><br/>- <b>Superclass</b> is where we have all the <b>common</b> things between the <b>Subclasses</b>." +
                        "<br/><br/>- Like Oranges, Apples and Bananas are sweet and are grown from a seed!" +
                        "<br/><br/>- So now, do you understand what is <b>inheritance</b>?" +
                        "<br/><br/>- Why don't you start the quiz and check yourself up!";
        return description;
    }

    public Map<Integer, String> questions(){
        Map<Integer, String> questions = new HashMap<>();

        String q1 = "<b>Question 1</b>: <br/><br/>  Which of the options is/are the Subclass of <b>Vehicle?</b>";
        String q2 = "<b>Question 2</b>: <br/><br/>  Which of the options is the Superclass of <b>Apple</b>?";
        String q3 = "<b>Question 3</b>: <br/><br/> Is the following a correct 'is-a' relationship?" +
                "<br/><br/><u>Cats</u> and <u>Dogs</u> are <u>Mammals</u>.<br/><br/>";
        String q4 = "<b>Question 4</b>: <br/><br/> Is the following a correct 'is-a' relationship?" +
                "<br/><br/><u>Water</u> is a <u>Fish</u>.<br/><br/>";
        String q5 = "<b>Question 5</b>: <br/><br/> Is the following a correct 'is-a' relationship?" +
                "<br/><br/><u>Pastas</u>, <u>Cookies</u> and <u>Fruits</u> are <u>Food</u>.<br/><br/>";

        questions.put(1, q1);
        questions.put(2, q2);
        questions.put(3, q3);
        questions.put(4, q4);
        questions.put(5, q5);

        return questions;
    }

    public Map<Integer, String[]> options(){
        Map<Integer, String[]> options = new HashMap<>();


        String[] q1 = {"SportsCar", "Truck", "All of them", "Bicycle", "Bus", "Train"};
        String[] q2 = {"Color", "Apple", "Banana", "Red", "None of above", "Fruits"};
        String[] q3 = {"Yes", "No"};

        options.put(1, q1);
        options.put(2, q2);
        options.put(3, q3);
        options.put(4, q3);
        options.put(5, q3);

        return options;
    }

    public Map<Integer, Integer> answers(){
        Map<Integer, Integer> answers = new HashMap<>();

        int q1 = 3;
        int q2 = 6;
        int q3 = 1;
        int q4 = 3;
        int q5 = 1;

        answers.put(1, q1);
        answers.put(2, q2);
        answers.put(3, q3);
        answers.put(4, q4);
        answers.put(5, q5);

        return answers;
    }

}
