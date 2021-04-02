package markus.wieland.hangman;

import markus.wieland.games.game.GameBoardInteractionListener;

public interface HangmanGameBoardInteractionListener extends GameBoardInteractionListener<HangmanGameBoardField> {
    void onClick(HangmanGameBoardField hangmanGameBoardField);
}
