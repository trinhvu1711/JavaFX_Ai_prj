package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements EventHandler<MouseEvent> {
	private final BottomView bottomView;
	private final CenterView centerView;
	private final View view;
	ArrayList<Student> students;
	String text;
	VoiceModel model;
	int id = 0;
	double score = 0;
	boolean add = false;
	boolean fill = false;
	File file;

	public Controller() {
		bottomView = new BottomView(this);
		centerView = new CenterView(this);
		model = new VoiceModel();
		view = new View(bottomView, centerView);
		students = new ArrayList<>();
//		Scene scene = new Scene(getView());
//		stage.setScene(scene);
	}

	@Override
	public void handle(MouseEvent event) {
		text = bottomView.getText().getText();
		if (text.equals("") || text == null) {
			text = model.getVoice();
			centerView.addChatHuman(text);
			model.chatLog.add("human|" + text);

		} else {
			model.chatLog.add("human|" + text);
			bottomView.getText().clear();
			centerView.addChatHuman(text);
		}
		try {
			System.out.println(text);
			function(text);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BottomView getBottomView() {
		return bottomView;
	}

	public CenterView getCenterView() {
		return centerView;
	}

	public View getView() {
		return view;
	}

	public void function(String command) throws URISyntaxException, IOException {
		if (command.toLowerCase().contains("google")) {
			model.chatLog.add(openGoogle());
			centerView.addChatBot("I'll open Google Chrome...");
			return;
		}
		if (command.toLowerCase().contains("youtube")) {
			model.chatLog.add(openYoutube());
			centerView.addChatBot("I'll open Youtube...");
			return;
		}
		if (command.toLowerCase().contains("name")) {
			model.chatLog.add(whatYourName());
			centerView.addChatBot("My name is Assistant");
			return;

		}
		if (command.toLowerCase().contains("begin")) {
			centerView.addChatBot("Begin add point");
			add = true;
			load();
			return;
		}
		if (command.toLowerCase().contains("grade") && fill) {
			System.out.println("Grade =?");
			centerView.addChatBot("Grade =?");
			score = score();
			add = false;
			addScore(id, score);
			return;
		}
		if (command.toLowerCase().contains("id") && add) {
			System.out.println("ID =?");
			centerView.addChatBot("ID =?");
			id = id();
			fill = true;
			return;
		}

		else {
			model.chatLog.add(notHear());
			centerView.addChatBot("Excuse me, could you repeat the question?");
			return;
		}
	}

	public String openGoogle() throws URISyntaxException, IOException {
		Desktop desk = Desktop.getDesktop();
		System.out.println("Opening: Google");
		URI uri = new URI("http://google.com/");
		desk.browse(uri);
		return "bot|I'll open Google Chrome...";
	}

	public String openYoutube() throws URISyntaxException, IOException {
		Desktop desk = Desktop.getDesktop();
		System.out.println("Opening: Youtube");
		URI uri = new URI("http://youtube.com/");
		desk.browse(uri);
		return "bot|I'll open Youtube...";
	}

	public String whatYourName() {
//		log.append("-Assistant: My name Assistant");
		return "bot|My name is Assistant";
	}

	public String notHear() {
//		log.append("-Assistant: Excuse me, could you repeat the question?\n");
		return "bot|Excuse me, could you repeat the question?";
	}

	public String addScore(int r, double score) {
		try {
			for (Student s : students) {
				if (Integer.parseInt(s.getId()) == r) {
					s.setPoint(score);
				}
			}
			System.out.println(students.get(r));
			save(file.getAbsolutePath(), students);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return "add score fail";
		}
		return "add score success";

	}

	public int id() throws IOException {
		int id = 0;

		try {
			String id_raw = "";
			id_raw = model.getVoice();
			System.out.println("-" + id_raw);
			id = Integer.parseInt(id_raw);
			centerView.addChatHuman("" + id);
			centerView.addChatBot("Id is: " + id);
		} catch (Exception e) {
			centerView.addChatBot("ID not true type");
		}
		return id;
	}

	public double score() throws IOException {
		double score = 0;
		String score_raw = model.getVoice();
		System.out.println(score_raw);
		try {
			score = Double.parseDouble(score_raw);
			centerView.addChatHuman("" + score);
			centerView.addChatBot("Add grade: " + score + " with ID: " + id);
		} catch (Exception e) {
			centerView.addChatBot("Grade not true type");
		}
		return score;
	}

	public void load() throws NumberFormatException, IOException {
		FileChooser fileChooser = new FileChooser();
		file = new FileChooser().showOpenDialog(new Stage());
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] row = line.split(";");
			String id = row[0];
			String score_raw = row[2];
			double point = Double.parseDouble(score_raw);
			String name = row[1];
			Student st = new Student(id, name, point);
			students.add(st);
		}
		br.close();
	}

	public void save(String path, ArrayList<Student> students) throws FileNotFoundException {
		File f = new File(path);
		if (!f.exists())
			return;
		PrintWriter pw = new PrintWriter(f);
		for (Student s : students) {
			pw.print(s.getId() + ";");
			pw.print(s.getName() + ";");
			pw.println(s.getPoint());
		}
		System.out.println("done");
		pw.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("D:\\ds.csv");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		ArrayList<Student> stList = new ArrayList<>();
		try {
			while ((line = br.readLine()) != null) {
				String[] row = line.split(";");
				String id = row[0];
				String score_raw = row[2];
				double point = Double.parseDouble(score_raw);
				String name = row[1];
				System.out.println(id + " " + name + " " + point);
				Student st = new Student(id, name, point);
				stList.add(st);
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println(e);
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(stList.get(0));
	}
}
