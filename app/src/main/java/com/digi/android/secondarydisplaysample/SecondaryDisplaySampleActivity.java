package com.digi.android.secondarydisplaysample;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SecondaryDisplaySampleActivity extends Activity {
	
	// Variables.
	private EditText textToDisplay;
	
	private RadioButton showTextButton;
	private RadioButton hideTextButton;
	
	private Button drawTextButton;
	private Button clearTextButton;
	
	private Spinner textSizeSpinner;
	private Spinner textColorSpinner;
	
	private BlackBoard blackBoard;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Initialize UI components.
		initializeUIComponents();
		// Initialize secondary display.
		initializeBlackBoard();
	}
	
	/**
	 * Initializes the UI components with their event listeners.
	 */
	private void initializeUIComponents() {
		// Display text.
		textToDisplay = (EditText)findViewById(R.id.text_to_display);
		// Draw text button.
		drawTextButton = (Button)findViewById(R.id.draw_text_button);
		drawTextButton.setOnClickListener(new OnClickListener() {
			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			public void onClick(View v) {
				handleDrawButtonPressed();
			}
		});
		// Clear text button.
		clearTextButton = (Button)findViewById(R.id.clear_text_button);
		clearTextButton.setOnClickListener(new OnClickListener() {
			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			public void onClick(View v) {
				handleClearButtonPressed();
			}
		});
		// Show text radio button.
		showTextButton = (RadioButton)findViewById(R.id.show_text_button);
		showTextButton.setOnClickListener(new OnClickListener() {
			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			public void onClick(View v) {
				handleShowTextButtonPressed();
			}
		});
		// Hide text radio button.
		hideTextButton = (RadioButton)findViewById(R.id.hide_text_button);
		hideTextButton.setOnClickListener(new OnClickListener() {
			/*
			 * (non-Javadoc)
			 * @see android.view.View.OnClickListener#onClick(android.view.View)
			 */
			public void onClick(View v) {
				handleHideTextButtonPressed();
			}
		});
		// Text size spinner.
		textSizeSpinner = (Spinner)findViewById(R.id.text_size_spinner);
		textSizeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
			 */
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				handleTextSizeSpinnerSelection();
			}
			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
			 */
			public void onNothingSelected(AdapterView<?> arg0) {
				// We will not implement anything here.
			}
		});
		// Text color spinner.
		textColorSpinner = (Spinner)findViewById(R.id.text_color_spinner);
		textColorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
			 */
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				handleTextColorSpinnerSelection();
			}
			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
			 */
			public void onNothingSelected(AdapterView<?> arg0) {
				// We will not implement anything here.
			}
		});
	}
	
	/**
	 * Initializes the black board in the secondary display.
	 */
	private void initializeBlackBoard() {
		DisplayManager displayManager = (DisplayManager)getSystemService(Context.DISPLAY_SERVICE);
		Display[] displays = displayManager.getDisplays(DisplayManager.DISPLAY_CATEGORY_PRESENTATION);
		if (displays.length > 0)
			blackBoard = new BlackBoard(this, displays[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onRestart()
	 */
	protected void onResume() {
		super.onResume();
		// Show the black board.
		if (blackBoard != null)
			blackBoard.show();
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	protected void onStop() {
		super.onStop();
		// Dismiss the black board.
		if (blackBoard != null)
			blackBoard.dismiss();
	}
	
	/**
	 * Handles what happens when the draw button is pressed.
	 */
	private void handleDrawButtonPressed() {
		if (blackBoard != null)
			blackBoard.setText(textToDisplay.getText().toString());
	}
	
	/**
	 * Handles what happens when the clear button is pressed.
	 */
	private void handleClearButtonPressed() {
		if (blackBoard != null)
			blackBoard.clearText();
	}
	
	/**
	 * Handles what happens when the show text radio button is pressed.
	 */
	private void handleShowTextButtonPressed() {
		if (showTextButton.isChecked()) {
			hideTextButton.setChecked(false);
			if (blackBoard != null)
				blackBoard.setTextVisible(true);
		}
	}
	
	/**
	 * Handles what happens when the hide text radio button is pressed.
	 */
	private void handleHideTextButtonPressed() {
		if (hideTextButton.isChecked()) {
			showTextButton.setChecked(false);
			if (blackBoard != null)
				blackBoard.setTextVisible(false);
		}
	}
	
	/**
	 * Handles what happens when the text size spinner selection changes.
	 */
	private void handleTextSizeSpinnerSelection() {
		if (blackBoard != null)
			blackBoard.setTextSize(textSizeSpinner.getSelectedItemPosition());
	}
	
	/**
	 * Handles what happens when the text color spinner selection changes.
	 */
	private void handleTextColorSpinnerSelection() {
		if (blackBoard != null)
			blackBoard.setTextColor(textColorSpinner.getSelectedItemPosition());
	}
}