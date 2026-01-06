package blackjack.ui;

import blackjack.logic.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.InputStream;

public class GameView {

    private BlackjackGame game;
    private HBox dealerBox;
    private HBox playerBox;
    private Text statusText;

    public void start(Stage stage) {
        game = new BlackjackGame();

        // Create main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        // Dealer cards
        dealerBox = new HBox(10);
        dealerBox.setAlignment(Pos.CENTER);
        updateCards(dealerBox, game.getDealerHand());

        // Player cards
        playerBox = new HBox(10);
        playerBox.setAlignment(Pos.CENTER);
        updateCards(playerBox, game.getPlayerHand());

        // Buttons
        Button hit = new Button("Hit");
        Button stand = new Button("Stand");

        hit.setPrefWidth(80);
        stand.setPrefWidth(80);

        hit.setStyle("-fx-background-color: gold; -fx-font-weight: bold;");
        stand.setStyle("-fx-background-color: orange; -fx-font-weight: bold;");

        HBox buttonBox = new HBox(20, hit, stand);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        // Status text
        statusText = new Text("Your move");
        statusText.setFill(Color.WHITE);
        statusText.setFont(Font.font("Arial", 20));
        HBox statusBox = new HBox(statusText);
        statusBox.setAlignment(Pos.CENTER);
        statusBox.setPadding(new Insets(10));

        // Layout assembly
        VBox centerBox = new VBox(30, dealerBox, playerBox, buttonBox, statusBox);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        // Button actions
        hit.setOnAction(e -> {
            game.playerHit();
            updateCards(playerBox, game.getPlayerHand());
            statusText.setText(game.getResult());
            if (game.getPlayerHand().getValue() > 21) {
                hit.setDisable(true);
                stand.setDisable(true);
                updateCards(dealerBox, game.getDealerHand());
            }
        });

        stand.setOnAction(e -> {
            game.playerStand();
            hit.setDisable(true);
            stand.setDisable(true);
            updateCards(dealerBox, game.getDealerHand());
            statusText.setText(game.getResult());
        });

        // Scene and stage
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Blackjack");
        stage.show();
    }

    private void updateCards(HBox box, Hand hand) {
        box.getChildren().clear();
        for (Card c : hand.getCards()) {
            try {
                String path = "/cards/" + c.getImageName();
                InputStream is = getClass().getResourceAsStream(path);
                if (is == null) {
                    System.out.println("Missing: " + path);
                    box.getChildren().add(new Text(c.getRank() + " of " + c.getSuit()));
                    continue;
                }
                ImageView img = new ImageView(new Image(is));
                img.setFitWidth(80);
                img.setFitHeight(120);
                box.getChildren().add(img);
            } catch (Exception ex) {
                box.getChildren().add(new Text(c.getRank() + " of " + c.getSuit()));
            }
        }
    }
}
