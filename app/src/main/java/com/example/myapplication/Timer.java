package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Timer} factory method to
 * create an instance of this fragment.
 */
public class Timer extends Fragment {

    private TextView textViewRecipeName, textViewTimeRemaining;
    private ProgressBar progressBarTimer;
    private Button buttonStart, buttonPause, buttonReset;
    private SeekBar seekBarStages;

    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private long totalTimeMillis = 30 * 60 * 1000;
    private long timeLeftMillis = totalTimeMillis;
    private int progress = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        textViewRecipeName = view.findViewById(R.id.textViewRecipeName);
        textViewTimeRemaining = view.findViewById(R.id.textViewTimeRemaining);
        progressBarTimer = view.findViewById(R.id.progressBarTimer);
        buttonStart = view.findViewById(R.id.buttonStart);
        buttonPause = view.findViewById(R.id.buttonPause);
        buttonReset = view.findViewById(R.id.buttonReset);
        seekBarStages = view.findViewById(R.id.seekBarStages);

        progressBarTimer.setMax(100);

        setUpRecipeButtons(view);

        setUpTimerControlButtons();

        return view;
    }

    private void setUpRecipeButtons(View view) {
        Button buttonStrawberry = view.findViewById(R.id.buttonStrawberry);
        Button buttonVanilla = view.findViewById(R.id.buttonVanilla);
        Button buttonChocolate = view.findViewById(R.id.buttonChocolate);
        Button buttonLemon = view.findViewById(R.id.buttonLemon);
        Button buttonBlueberry = view.findViewById(R.id.buttonBlueberry);
        Button buttonMatcha = view.findViewById(R.id.buttonMatcha);

        View.OnClickListener recipeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                textViewRecipeName.setText(button.getText());
                resetTimer();
            }
        };

        buttonStrawberry.setOnClickListener(recipeClickListener);
        buttonVanilla.setOnClickListener(recipeClickListener);
        buttonChocolate.setOnClickListener(recipeClickListener);
        buttonLemon.setOnClickListener(recipeClickListener);
        buttonBlueberry.setOnClickListener(recipeClickListener);
        buttonMatcha.setOnClickListener(recipeClickListener);
    }

    private void setUpTimerControlButtons() {

        buttonStart.setOnClickListener(v -> startTimer());

        buttonPause.setOnClickListener(v -> pauseTimer());

        buttonReset.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        if (!isTimerRunning) {
            countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftMillis = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    isTimerRunning = false;
                    textViewTimeRemaining.setText("Time's Up!");
                }
            }.start();

            isTimerRunning = true;
        }
    }

    private void pauseTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }

    private void resetTimer() {
        if (isTimerRunning) {
            countDownTimer.cancel();
        }


        timeLeftMillis = totalTimeMillis;
        updateTimer();
        progressBarTimer.setProgress(0); // Reset ProgressBar
        isTimerRunning = false;
    }

    private void updateTimer() {
        // Calculate minutes and seconds remaining
        int minutes = (int) (timeLeftMillis / 1000) / 60;
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        // Update the timer TextView
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        textViewTimeRemaining.setText(timeLeftFormatted);


        progress = (int) ((totalTimeMillis - timeLeftMillis) * 100 / totalTimeMillis);
        progressBarTimer.setProgress(progress);


        seekBarStages.setProgress(progress);
    }
}