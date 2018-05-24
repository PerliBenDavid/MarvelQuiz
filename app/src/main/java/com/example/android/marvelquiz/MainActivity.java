package com.example.android.marvelquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    CheckBox question4Answer1, question4Answer2, question4Answer3, question4Answer4;
    EditText question5Answer1;
    LinearLayout question4, question5;
    RadioGroup question1, question2, question3;
    RadioButton question1Answer2, question2Answer1, question3Answer3;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question1Answer2 = findViewById(R.id.q1Answer2);
        question2Answer1 = findViewById(R.id.q2Answer1);
        question3Answer3 = findViewById(R.id.q3Answer3);
        question4Answer1 = findViewById(R.id.q4Answer1);
        question4Answer2 = findViewById(R.id.q4Answer2);
        question4Answer3 = findViewById(R.id.q4Answer3);
        question4Answer4 = findViewById(R.id.q4Answer4);
        question5Answer1 = findViewById(R.id.q5Answer1);
        question1 = findViewById(R.id.first_question);
        question2 = findViewById(R.id.second_question);
        question3 = findViewById(R.id.third_question);
        question4 = findViewById(R.id.fourth_question);
        question5 = findViewById(R.id.fifth_question);
    }

    /**
     * This method checks if the correct answer is selected
     * If so, it is added 1 to the counter
     * If not, it paints the question in red
     */
    public void updateCounterIfChecked(RadioButton radioButton , RadioGroup radioGroup) {
        if (radioButton.isChecked()) {
            counter++;
            radioGroup.setBackgroundColor(getColor(R.color.rightAnswer));
        }else {
            radioGroup.setBackgroundColor(getColor(R.color.wrongAnswer));
        }
    }

    /**
     * This method Checks whether all the correct answers are checked and the wrong answers are not
     * If so, it is added 1 to the counter
     * If not, it paints the question in red
     */
    public void updateCounterIfChecked(CheckBox checkBox1, CheckBox checkBox2, CheckBox checkBox3, LinearLayout linearLayout) {
        if (checkBox1.isChecked() && checkBox2.isChecked() && checkBox3.isChecked()  && question4Answer3.isChecked() == false) {
            counter++;
            linearLayout.setBackgroundColor(getColor(R.color.rightAnswer));
        }else {
            linearLayout.setBackgroundColor(getColor(R.color.wrongAnswer));
        }
    }

    public void CheckTheAnswers(View view) {
        updateCounterIfChecked(question1Answer2, question1);
        updateCounterIfChecked(question2Answer1, question2);
        updateCounterIfChecked(question3Answer3, question3);
        updateCounterIfChecked(question4Answer1, question4Answer2, question4Answer4, question4);

        /**
         * check if the typed is the correct answer
         * If so, it is added 1 to the counter
         * If not, it paints the question in red
         */
        String q5A1 = question5Answer1.getText().toString();
        if (q5A1.equals(getString(R.string.thor))) {
            counter++;
            question5.setBackgroundColor(getColor(R.color.rightAnswer));
        }else {
            question5.setBackgroundColor(getColor(R.color.wrongAnswer));
        }

        String userAnsweredWell = getString(R.string.you_answered, counter) + "\n" + getString(R.string.excellent);
        String userAnsweredNotWell = getString(R.string.you_answered, counter) + "\n" + getString(R.string.try_again);

        /**
         * When the button is pressed A message appears stating how
         * many questions you answered correctly
         * if less than 3 correct answers the message will appear in red more than 3 in blue
         */
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        TextView tv = new TextView(this);
        tv.setTextSize(30);
        tv.setTextColor(Color.WHITE);
        if (counter > 3) {
            tv.setBackgroundColor(Color.BLUE);
            tv.setText(userAnsweredWell);
        } else {
            tv.setBackgroundColor(Color.RED);
            tv.setText(userAnsweredNotWell);
        }
        toast.setView(tv);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
        counter = 0;

    }
}
