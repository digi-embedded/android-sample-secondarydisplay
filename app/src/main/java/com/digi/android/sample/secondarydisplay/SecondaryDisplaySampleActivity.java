/**
 * Copyright (c) 2014-2016, Digi International Inc. <support@digi.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.digi.android.sample.secondarydisplay;

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

/**
 * Secondary display sample application.
 *
 * <p>This application demonstrates the usage of the Secondary Display API in Android
 * by giving an example of controlling the contents of the secondary display using
 * controls in the primary screen.</p>
 *
 * <p>For a complete description on the example, refer to the 'README.md' file
 * included in the example directory.</p>
 */
public class SecondaryDisplaySampleActivity extends Activity {
	
	// Variables.
	private EditText textToDisplay;
	
	private RadioButton showTextButton;
	private RadioButton hideTextButton;

	private Spinner textSizeSpinner;
	private Spinner textColorSpinner;
	
	private BlackBoard blackBoard;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Initialize UI components.
		initializeUIComponents();
		// Initialize secondary display.
		initializeBlackBoard();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Show the black board.
		if (blackBoard != null)
			blackBoard.show();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Dismiss the black board.
		if (blackBoard != null)
			blackBoard.dismiss();
	}
	
	/**
	 * Initializes the UI components with their event listeners.
	 */
	private void initializeUIComponents() {
		// Display text.
		textToDisplay = (EditText)findViewById(R.id.text_to_display);
		// Draw text button.
		Button drawTextButton = (Button) findViewById(R.id.draw_text_button);
		drawTextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleDrawButtonPressed();
			}
		});
		// Clear text button.
		Button clearTextButton = (Button) findViewById(R.id.clear_text_button);
		clearTextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleClearButtonPressed();
			}
		});
		// Show text radio button.
		showTextButton = (RadioButton)findViewById(R.id.show_text_button);
		showTextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleShowTextButtonPressed();
			}
		});
		// Hide text radio button.
		hideTextButton = (RadioButton)findViewById(R.id.hide_text_button);
		hideTextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleHideTextButtonPressed();
			}
		});
		// Text size spinner.
		textSizeSpinner = (Spinner)findViewById(R.id.text_size_spinner);
		textSizeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				handleTextSizeSpinnerSelection();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// We will not implement anything here.
			}
		});
		// Text color spinner.
		textColorSpinner = (Spinner)findViewById(R.id.text_color_spinner);
		textColorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				handleTextColorSpinnerSelection();
			}
			@Override
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