package ViewAndControl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** The View of the MVC pattern
 *  The GUI of the game
 *
 *  @author Quan Do*/
public class View {
    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    private BorderPane borderPane;
    private TextField scoreField;
    private Button undoButton;
    private Button newGameButton;

    /** Constructor, set up the fields and display GUI*/
    public View(Stage stage){
        stage.setTitle("2048");

        // Construct the gui
        this.stage = stage;
        gridPane = new GridPane();
        borderPane = new BorderPane();
        borderPane.setCenter(gridPane);
        scene = new Scene(borderPane, 590, 570);

        // set undo button
        undoButton = new Button();
        undoButton.setText("Undo");
        undoButton.setMinSize(100, 100);
        undoButton.setFocusTraversable(false);

        // set new game button
        newGameButton = new Button();
        newGameButton.setText("New Game");
        newGameButton.setMinSize(100, 100);
        newGameButton.setFocusTraversable(false);

        // set VBox and add the buttons
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20,20,20,0));
        vBox.getChildren().addAll(undoButton, newGameButton);
        borderPane.setRight(vBox);

        // set score field
        scoreField = new TextField();
        scoreField.setMinSize(50,100);
        scoreField.setText("Score \n 0");
        scoreField.setAlignment(Pos.CENTER);
        scoreField.setEditable(false);
        scoreField.setMouseTransparent(true);
        scoreField.setFocusTraversable(false);
        borderPane.setTop(scoreField);

        // Add spacing between text fields
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add the scene to stage and display it
        stage.setScene(scene);
        stage.show();
    }

    /** Get the Stage of the View
     *
     * @return the stage of the View
     */
    public Stage getStage() {
        return stage;
    }

    /** Get the GridPane of the View
     *
     * @return the GridPane of the View
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    /** Get the Scene of the View
     *
     * @return the Scene of the View
     */
    public Scene getScene() {
        return scene;
    }

    /** Get the scoreField of the View
     *
     * @return the scoreField of the View
     */
    public TextField getScoreField() {
        return scoreField;
    }

    /** Get the undoButton of the View
     *
     * @return the undoButton of the View
     */
    public Button getUndoButton() {
        return undoButton;
    }

    /** Get the BorderPane of the View
     *
     * @return the BorderPane of the View
     */
    public BorderPane getBorderPane() {
        return borderPane;
    }

    /** Get the newGameButton of the View
     *
     * @return the newGameButton of the View
     */
    public Button getNewGameButton() {
        return newGameButton;
    }

    /** Set the Stage of the View
     *
     * @param stage the stage which will be set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /** Set the GridPane of the View
     *
     * @param gridPane the GridPane which will be set
     */
    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    /** Set the BorderPane of the View
     *
     * @param borderPane the BorderPane which will be set
     */
    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    /** Set the scoreField of the View
     *
     * @param scoreField the scoreField which will be set
     */
    public void setScoreField(TextField scoreField) {
        this.scoreField = scoreField;
    }

    /** Set the undoButton of the View
     *
     * @param undoButton the undoButton which will be set
     */
    public void setUndoButton(Button undoButton) {
        this.undoButton = undoButton;
    }

    /** Set the newGameButton of the View
     *
     * @param newGameButton the newGameButton which will be set
     */
    public void setNewGameButton(Button newGameButton) {
        this.newGameButton = newGameButton;
    }

    /** Set the Scene of the View
     *
     * @param scene the Scene which will be set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
