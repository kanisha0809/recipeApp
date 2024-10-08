package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Feedback} factory method to
 * create an instance of this fragment.
 */
public class Feedback extends Fragment {

    private RatingBar ratingBar;
    private EditText editTextReview;
    private CheckBox checkBoxShare;
    private Button buttonSubmit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        ratingBar = view.findViewById(R.id.ratingBar);
        editTextReview = view.findViewById(R.id.editTextReview);
        checkBoxShare = view.findViewById(R.id.checkBoxShare);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(v -> submitFeedback());

        return view;
    }

    private void submitFeedback() {
        float rating = ratingBar.getRating();
        String review = editTextReview.getText().toString();
        boolean isShared = checkBoxShare.isChecked();

        // Validate input
        if (rating == 0) {
            Toast.makeText(getContext(), "Please provide a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        String feedbackMessage = "Thank you for your feedback! Rating: " + rating + ", Review: " + review + (isShared ? " (Shared)" : " (Not Shared)");

        Toast.makeText(getContext(), feedbackMessage, Toast.LENGTH_LONG).show();

        ratingBar.setRating(0);
        editTextReview.setText("");
        checkBoxShare.setChecked(false);
    }
}