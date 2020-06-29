package bowling;

import bowling.domain.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGame {
    public static final int FIRST_ROUND = 1;
    public static final int FINAL_ROUND = 10;

    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayerName(), FINAL_ROUND);

        ResultView.printBoard(player);
        for (int round = FIRST_ROUND; round <= FINAL_ROUND; round++) {
            playRound(round, player);
        }
    }

    private static void playRound(int round, Player player) {
        boolean isFrameFinish = player.bowling(InputView.getPin(round));
        ResultView.printBoard(player);
        if (isFrameFinish) {
            return;
        }
        playRound(round, player);
    }
}
