package com.example.sherisesinyeelam.java4kids.SettingsPage;

import android.app.Dialog;
import android.text.Html;

import com.example.sherisesinyeelam.java4kids.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class QandA{

    Dialog dialog;

    public QandA(Dialog dialog){
        this.dialog = dialog;
    }

    public void getQandA(){
        q1();
        q2();
        q3();
    }

    public void q1(){
        // sample code snippet to set the text content on the ExpandableTextView
        ExpandableTextView expTv1 = (ExpandableTextView) dialog.findViewById(R.id.expand_text_view_q1);
        String qa1 = "<b><u>Question 1 - What is Java4Kids?</u></b>" +
                "<br/><br/>" +
                "Java4Kids is an educational application that designed and made by an university student" +
                " for children from age of 7 to learn Java programming concept in a less stress and fun way. ";
        // IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        expTv1.setText(Html.fromHtml(qa1));
    }

    public void q2(){
        ExpandableTextView expTv1 = (ExpandableTextView) dialog.findViewById(R.id.expand_text_view_q2);
        String qa2 = "<b><u>Question 2 - What can I do here?</u></b>" +
                "<br/><br/>" +
                "In Java4Kids, children will be able to learn basic concept of Java programming through " +
                "different quiz games." +
                "<br/>Such as what is variables and methods.";
        expTv1.setText(Html.fromHtml(qa2));
    }

    public void q3(){
        ExpandableTextView expTv1 = (ExpandableTextView) dialog.findViewById(R.id.expand_text_view_q3);
        String qa3 = "<b><u>Question 3 - How to Login or Register in Java4Kids?</u></b>" +
                "<br/><br/>" +
                "User can chose to play the application as a guest without logging in or register an account." +
                "<br/>To <b><u>login</u></b>, you can click <b>Login</b> button at the <b>Starting page</b> or the <b>Login</b> in <b>Settings</>." +
                "<br/>To <b><u>register</u></b>, you can click <b><u>register me-></u></b> below the Login button at the login page.";
        expTv1.setText(Html.fromHtml(qa3));
    }
}

// Manabu, S. (2014). Manabu-GT/ExpandableTextView [online]. Available at https://github.com/Manabu-GT/ExpandableTextView [accessed 16/03/2019]
