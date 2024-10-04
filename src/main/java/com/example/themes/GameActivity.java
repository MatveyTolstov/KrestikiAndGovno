package com.example.themes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private Button[] buttons;
    private TextView textView;
    private final String krest = "X";
    private final String nolik = "O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };
        textView = findViewById(R.id.textWin);
    }

    public void playerMove(View v) {
        Button button = (Button) v;
        if (button.getText().toString().isEmpty() && !isGameOver()) {
            button.setText(krest);
            if (!isPlayerWinner() && !isGameOver()) {
                PcPlay();
            }
        }
    }

    private boolean isPlayerWinner() {
        if (checkWin(krest)) {
            textView.setText("You Win!");
            return true;
        }
        return false;
    }

    private boolean isPcWinner() {
        if (checkWin(nolik)) {
            textView.setText("PC Wins!");
            return true;
        }
        return false;
    }

    private boolean checkWin(String player) {
        return (buttons[0].getText().toString().equals(player) && buttons[1].getText().toString().equals(player) && buttons[2].getText().toString().equals(player)) || // Row 1
                (buttons[3].getText().toString().equals(player) && buttons[4].getText().toString().equals(player) && buttons[5].getText().toString().equals(player)) || // Row 2
                (buttons[6].getText().toString().equals(player) && buttons[7].getText().toString().equals(player) && buttons[8].getText().toString().equals(player)) || // Row 3
                (buttons[0].getText().toString().equals(player) && buttons[3].getText().toString().equals(player) && buttons[6].getText().toString().equals(player)) || // Column 1
                (buttons[1].getText().toString().equals(player) && buttons[4].getText().toString().equals(player) && buttons[7].getText().toString().equals(player)) || // Column 2
                (buttons[2].getText().toString().equals(player) && buttons[5].getText().toString().equals(player) && buttons[8].getText().toString().equals(player)) || // Column 3
                (buttons[0].getText().toString().equals(player) && buttons[4].getText().toString().equals(player) && buttons[8].getText().toString().equals(player)) || // Diagonal \
                (buttons[2].getText().toString().equals(player) && buttons[4].getText().toString().equals(player) && buttons[6].getText().toString().equals(player));   // Diagonal /
    }

    private void PcPlay() {
        List<Button> availableButtons = getAvailableButtons();

        if (!availableButtons.isEmpty()) {
            Button pcButton = availableButtons.get(new Random().nextInt(availableButtons.size()));
            pcButton.setText(nolik);
            isPcWinner();
        }
    }

    private List<Button> getAvailableButtons() {
        List<Button> availableButtons = new ArrayList<>();
        for (Button button : buttons) {
            if (button.getText().toString().isEmpty()) {
                availableButtons.add(button);
            }
        }
        return availableButtons;
    }

    private boolean isGameOver() {
        return getAvailableButtons().isEmpty() || isPlayerWinner() || isPcWinner();
    }
}
