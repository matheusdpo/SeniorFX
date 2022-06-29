package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXController implements Initializable {

	@FXML
	private Button btnDone;

	@FXML
	private TextField txtFieldNF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void converter() {
		String NF = txtFieldNF.getText();

		Path seniorProcessado = Paths
				.get("\\\\Seniorprd\\SeniorPRD\\sde\\integracao\\dolomia_filial 1\\Processado\\Nfe");

		Path seniorProcessar = Paths.get("\\\\Seniorprd\\SeniorPRD\\sde\\integracao\\dolomia_filial 1\\Processar\\Nfe");

		String NFinal = "NFe3522067227725400016255001000" + NF + "([0-9])+" + "_" + "([0-9])+" + ".xml";

		try (DirectoryStream<Path> findNF = Files.newDirectoryStream(seniorProcessado, NFinal)) {
			for (Path path : findNF) {
				if (path.getFileName().toString().equals(NFinal)) {
					Files.copy(seniorProcessado.resolve(NFinal), seniorProcessar.resolve(NFinal));
					Alert a = new Alert(AlertType.CONFIRMATION);
					a.setTitle("Senior Fixer");
					a.setHeaderText("Concluído!");
					a.setContentText("Nota Fiscal: " + NF + " devidamente ajustada!" + "\n"
							+ "Aguarde alguns segundos para que ela possa ser liberada no sde.");
					a.show();
				}
			}
		} catch (IOException e) {
			Alert a = new Alert(AlertType.WARNING);
			a.setTitle("Senior Fixer");
			a.setHeaderText("Erro!");
			a.setContentText(e.toString());
			a.show();
		}
	}

	public void donation() throws Exception {
		URI link = new URI("https://linktr.ee/matheusdpo_");
		Desktop.getDesktop().browse(link);
	}
}
