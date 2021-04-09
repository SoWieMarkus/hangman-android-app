package markus.wieland.hangman;

import markus.wieland.games.game.view.GameBoardInteractionListener;

public interface HangmanGameBoardInteractionListener extends GameBoardInteractionListener {
    void onClick(HangmanGameBoardFieldView hangmanGameBoardField);
}
