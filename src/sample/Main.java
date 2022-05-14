package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private String whoseTurn = "X";
    private Text gameStatus = new Text("x's turn ");
    private Cell[][] cells = new Cell[3][3];

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        for(int i = 0; i < 3; i++){
           for(int j = 0; j < 3; j++){
               pane.add(cells[i][j]=new Cell(),i,j);
           }
        }
        StackPane stackPane = new StackPane(gameStatus);
        gameStatus.setFont(Font.font(60));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(stackPane);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(new Scene(borderPane, 600, 700));
        primaryStage.show();
    }
    public boolean isWon(String token){
        for(int i = 0; i < 3; i++){
            if(cells[i][0].getToken() == token
            && cells[i][1].getToken() == token
            && cells[i][2].getToken() ==token)
                return true;
        }
        for(int i = 0; i< 3; i++){
            if(cells[0][i].getToken() == token
            && cells[1][i].getToken() == token
            && cells[2][i].getToken() == token)
                return true;
        }
        if(cells[0][0].getToken() == token
        && cells[1][1].getToken() == token
        && cells[2][2].getToken() == token)
            return true;
        else if(cells[0][2].getToken() == token
                && cells[1][1].getToken() == token
                && cells[2][0].getToken() == token)
            return true;
        return false;
    }
    public boolean isFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(cells[i][j].getToken() == " " )
                    return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        launch(args);
    }
    public class Cell extends StackPane {
        private Text token = new Text(" ");
        public Cell(){
            Rectangle rectangle = new Rectangle(200,200);
            rectangle.setFill(null);
            rectangle.setStroke(Color.BLACK);
            token.setFont(Font.font(60));
            setAlignment(Pos.CENTER);
            getChildren().addAll(rectangle,token);
            setOnMouseClicked(e -> handleMouseClicked());
        }

        public void setToken(String token) {
           this.token.setText(token);
        }
        public String getToken(){
            return token.getText();
        }
        public void handleMouseClicked() {
          if(token.getText() == " " && whoseTurn != " "){
              setToken(whoseTurn);
          }
          if(isWon(token.getText())){
              gameStatus.setText( whoseTurn+" won! game is over");
              whoseTurn = " ";
          }
          else if(isFull()){
              gameStatus.setText("draw! game is over");
              whoseTurn = " ";
          }
          else {
              whoseTurn = (whoseTurn == "X" ? "O" :"X");
              gameStatus.setText(whoseTurn+"'s turn");
          }
        }


    }
}
