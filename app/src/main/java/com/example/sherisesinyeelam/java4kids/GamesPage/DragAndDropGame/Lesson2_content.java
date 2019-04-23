package com.example.sherisesinyeelam.java4kids.GamesPage.DragAndDropGame;

import com.example.sherisesinyeelam.java4kids.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lesson2_content {

    public int lesson_icon(){
        return R.drawable.variables_icon;
    }

    public String lesson_name(){
        return "Variable Types";
    }

    public String lesson_description(){
        String description =
                "- <b>Variable types</b> is about the <b>type of a value</b> that being stored." +
                        "<br/><br/>- To think it this way: <b>variable</b> is a <i><u>file</u></i> to used to put something inside." +
                        "We have pencil case for pens and we have fridge to store cold food. In this case, " +
                        "<u><i>pencil case</i></u> will be the <b>variable</b> and the <b>vales</b> will be <u><i>pens and pencil</i></u>." +
                        "<br/><br/>- But then what is <b>variable types</b>?" +
                        "<br/><br/>- It means the type of things we put into the file, Such as <i><u>pens and pencils</i></u> are <u><i>stationery</i></u>. And <u><i>stationery</i></u> will be the <b>type</b>." +
                        "<br/><br/>- In Java, there are mainly 5 types of values:" +
                        "<br/><br/>. String: any <i><i>word</i></u> is a string and in Java, a String has to be quoted with speech mark <b>\" \"</b>. Such as \"Yummy\" and \"Tasty\"." +
                        "<br/><br/>. int: means <i><i>integer</i></u>, a whole number. Such as 63, 1, 7." +
                        "<br/><br/>. double: means numbers with <i><i>decimals</i></u>. Such as 0.86, 3.147" +
                        "<br/><br/>. boolean: is either <i><i>true</i></u> or <i><i>false</i></u>." +
                        "<br/><br/>. char: means <u><i>characters</i></u>, a letter. Character has to use a single quote <b>\' \'</b> to define. Such as 'y' and 'n'." +
                        "<br/><br/>- So now, do you understand what are <b>types of variable</b>?" +
                        "<br/><br/>- Why don't you start the quiz and check yourself up!";
        return description;
    }

    public Map<Integer, String> questions(){
        Map<Integer, String> questions = new HashMap<>();

        String q1 = "<b>Question</b>: <br/><br/> Please <b>Drag</b> the correct <b>Value</b> to the correct <b>Type</b> below.";
        String q2 = "<b>Question</b>: <br/><br/> Please <b>Drag</b> the correct <b>Type</b> to the correct <b>Value</b> below.";

        questions.put(1, q1);
        questions.put(2, q2);
//        questions.put(3, q3);
//        questions.put(4, q4);
//        questions.put(5, q5);

        return questions;
    }

    public Map<Integer, String[]> setDropArea(){
        Map<Integer, String[]> dropArea = new HashMap<>();

        String[] q1 = {"String", "int", "float", "char", "boolean"};
        String[] q2 = {"\"false\"", "1", "true", "7.15", "\'x\'"};

        dropArea.put(1, q1);
        dropArea.put(2, q2);
//        options.put(3, q3);
//        options.put(4, q3);
//        options.put(5, q3);

        return dropArea;
    }

    public Map<Integer, String[]> setDragOptions(){
        Map<Integer, String[]> dragOptions = new HashMap<>();

        String[] q1 = {"0.5", "\'c\'", "true", "\"happy day\"", "100"};
        String[] q2 = {"float", "boolean", "int", "char", "String"};

        dragOptions.put(1, q1);
        dragOptions.put(2, q2);
//        options.put(3, q3);
//        options.put(4, q3);
//        options.put(5, q3);

        return dragOptions;
    }

    //todo: think how to apply this in drag and drop activity
    public Map<Integer, ArrayList<int[]>> answers(){
        Map<Integer, ArrayList<int[]>> answers = new HashMap<>();

        // {drag piece, drop area}
        ArrayList<int[]> q1Pairs = new ArrayList<>();
        int[] pair1 = {1,3};
        int[] pair2 = {2,4};
        int[] pair3 = {3,5};
        int[] pair4 = {4,1};
        int[] pair5 = {5,2};
        q1Pairs.add(pair1);
        q1Pairs.add(pair2);
        q1Pairs.add(pair3);
        q1Pairs.add(pair4);
        q1Pairs.add(pair5);

        ArrayList<int[]> q2Pairs = new ArrayList<>();

        int[] q2pair1 = {1,4};
        int[] q2pair2 = {2,3};
        int[] q2pair3 = {3,2};
        int[] q2pair4 = {4,5};
        int[] q2pair5 = {5,1};

        q2Pairs.add(q2pair1);
        q2Pairs.add(q2pair2);
        q2Pairs.add(q2pair3);
        q2Pairs.add(q2pair4);
        q2Pairs.add(q2pair5);

        answers.put(1, q1Pairs);
        answers.put(2, q2Pairs);

        return answers;
    }

}
