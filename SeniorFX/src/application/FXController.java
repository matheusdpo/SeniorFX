package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

	public void converter() throws IOException {
		if (btnDone.isDisable() == false) {
			btnDone.setDisable(true);
			System.out.println(
					"[SENIOR FIXER]....................................................................... BUTTON HAS BEEN DISABLED.");
		}

		String NF = txtFieldNF.getText();
		System.out.println(
				"[SENIOR FIXER]....................................................................... NF NUMBER: "
						+ NF);

		final Pattern regex = Pattern
				.compile("NFe3522067227725400016255001000" + NF + "([0-9])+" + "_" + "([0-9])+" + ".xml");

		String[] pathnames;

		File f = new File("\\\\Seniorprd\\SeniorPRD\\sde\\integracao\\dolomia_filial 1\\Processado\\Nfe");

		System.out.println(f.getPath());
		pathnames = f.list();

		for (String file : pathnames) {
			try {
				if (regex.matcher(file).find()) {
					System.out.println(
							"[SENIOR FIXER]....................................................................... THE FILE HAS BEEN FOUND: "
									+ file);
					FileInputStream fis = null;
					FileOutputStream fos = null;

					try {
						try {
							fis = new FileInputStream(
									"\\\\Seniorprd\\SeniorPRD\\sde\\integracao\\dolomia_filial 1\\Processado\\Nfe\\"
											+ file);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
							Alert a = new Alert(AlertType.ERROR);
							a.setTitle("Senior Fixer");
							a.setHeaderText("Erro!");
							a.setContentText("File not found");
							a.show();
						}

						try {
							fos = new FileOutputStream(
									"\\\\Seniorprd\\SeniorPRD\\sde\\integracao\\dolomia_filial 1\\Processar\\Nfe\\"
											+ file);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
							Alert a = new Alert(AlertType.ERROR);
							a.setTitle("Senior Fixer");
							a.setHeaderText("Erro!");
							a.setContentText("File not found");
							a.show();
						}

						int c;

						while ((c = fis.read()) != -1) {
							fos.write(c);
						}
						System.out.println(
								"[SENIOR FIXER]....................................................................... COPIED SUCCESSULLY.");
					}

					finally {
						if (fis != null) {
							fis.close();
						}
						if (fos != null) {
							fos.close();
						}

						if (btnDone.isDisable() == true) {
							btnDone.setDisable(false);
							System.out.println(
									"[SENIOR FIXER]....................................................................... BUTTON HAS BEEN ENABLED.");
						}

						Alert a = new Alert(AlertType.CONFIRMATION);
						a.setTitle("Senior Fixer");
						a.setHeaderText("Concluído!");
						a.setContentText("Nota Fiscal: " + NF + " devidamente ajustada!" + "\n"
								+ "Aguarde alguns segundos para que ela possa ser liberada no sde.");
						a.show();

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (btnDone.isDisable() == true) {
				btnDone.setDisable(false);
				System.out.println(
						"[SENIOR FIXER]....................................................................... BUTTON HAS BEEN ENABLED.");
			}
		}
	}

	public void donation() throws Exception {
		URI link = new URI("https://linktr.ee/matheusdpo_");
		Desktop.getDesktop().browse(link);
	}
}
