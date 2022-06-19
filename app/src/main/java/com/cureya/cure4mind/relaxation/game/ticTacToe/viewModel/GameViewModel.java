package com.cureya.cure4mind.relaxation.game.ticTacToe.viewModel;

import static com.cureya.cure4mind.relaxation.game.ticTacToe.util.StringUtility.stringFromNumbers;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cureya.cure4mind.relaxation.game.ticTacToe.model.Cell;
import com.cureya.cure4mind.relaxation.game.ticTacToe.model.Game;
import com.cureya.cure4mind.relaxation.game.ticTacToe.model.Player;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column, View view) {
        TextView textView = (TextView) view;

        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
            textView.setText(game.currentPlayer.value);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(view.getResources().getColor(android.R.color.tab_indicator_text, null));
            }else {
                textView.setTextColor(view.getResources().getColor(android.R.color.tab_indicator_text));
            }
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }
}
