package blackjack;

import javafx.application.Application;
import javafx.stage.Stage;
import blackjack.ui.GameView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GameView view = new GameView();
        view.start(stage);
    }

    public static void main(String[] args){
        launch();
    }
}
