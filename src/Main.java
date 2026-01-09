import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new LoginView().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
