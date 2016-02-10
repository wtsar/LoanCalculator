import javafx.application.*;
import java.text.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;

public class Calculator extends Application{
	private TextField principle;
	private TextField interest;
	private TextField years;
	private TextField resultPayments;
	private TextField resultInterest;
	private TextField resultTotal;
	private TextArea history;
	DecimalFormat df = new DecimalFormat("###,###,###.00");
	
	
	public static void main(String [] args){
		launch(args);
	}
	
	public void start(Stage stage){
		HBox main = new HBox();
		VBox right = new VBox();
		history = new TextArea();
		history.setPrefSize(310, 340);
		history.appendText("                             History\n");
		right.getChildren().add(history);
		right.setPadding(new Insets(20,20,40,40));
		Button clearHistory = new Button("Clear History");
		clearHistory.setOnAction((event)->clear());
		right.getChildren().add(clearHistory);
		VBox layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(20,10,100,30));
		
		HBox one = new HBox();
		principle = new TextField();
		principle.setOnAction((event)->Calculate());
		principle.setPrefSize(150, 35);
		Label amount = new Label("Loan Amount       $");
		amount.setPrefSize(150, 35);
		one.getChildren().addAll(amount, principle);
		
		HBox two = new HBox();
		interest = new TextField();
		interest.setOnAction((event)->Calculate());
		interest.setPrefSize(150, 35);
		Label interestLabel = new Label("Interest Rate        %");
		interestLabel.setPrefSize(150, 35);
		two.getChildren().addAll(interestLabel, interest);
		
		HBox three = new HBox();
		years = new TextField();
		years.setOnAction((event)->Calculate());
		years.setPrefSize(150, 35);
		Label yearsLabel = new Label("Loan Term in Years");
		yearsLabel.setPrefSize(150, 35);
		three.getChildren().addAll(yearsLabel, years);
		
		HBox four = new HBox();
		GridPane border = new GridPane();
		border.setHgap(1);
		border.setVgap(1);
		Button button = new Button("Calculate");
		button.setOnAction((event)->Calculate());
		Button clear = new Button("Clear");
		clear.setOnAction((event)->clearAll());
		border.setPrefWidth(300);
		border.add(button, 60,1);
		border.add(clear, 85, 1);
		four.getChildren().add(border);
		
		HBox five = new HBox();
		Label result = new Label("Monthly Payment");
		result.setPrefSize(150, 35);
		resultPayments = new TextField();
		resultPayments.setEditable(false);
		resultPayments.setPrefSize(150, 35);
		resultPayments.setStyle("-fx-background-color: #bfbfbf;");
		five.getChildren().addAll(result, resultPayments);
		
		HBox six = new HBox();
		Label totalInterest = new Label("Total Interest Paid");
		totalInterest.setPrefSize(150, 35);
		resultInterest = new TextField();
		resultInterest.setEditable(false);
		resultInterest.setPrefSize(150, 35);
		resultInterest.setStyle("-fx-background-color: #bfbfbf;");
		six.getChildren().addAll(totalInterest, resultInterest);
		
		HBox seven = new HBox();
		Label totalAmount = new Label("Total Amount Paid");
		totalAmount.setPrefSize(150, 35);
		resultTotal = new TextField();
		resultTotal.setEditable(false);
		resultTotal.setPrefSize(150, 35);
		resultTotal.setStyle("-fx-background-color: #bfbfbf;");
		seven.getChildren().addAll(totalAmount, resultTotal);
		
		
		layout.getChildren().addAll(one,two,three, four, five, six, seven);
		main.getChildren().addAll(layout,right);
		Scene scene = new Scene(main, 700, 425);
		scene.getStylesheets().add("Styles.css");
		stage.setScene(scene);
		stage.setTitle("Loan Calculator");
		stage.show();
	}
	

	public void Calculate(){
		String l = principle.getText();
		String loan = l.replaceAll(",","");
		String interestRate = interest.getText();
		String time = years.getText();
		double p;
		double i;
		double t;
		try{
			p = Double.parseDouble(loan);
			principle.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px;");
		}catch(Exception e){principle.setText(""); principle.setStyle("-fx-border-color: red; -fx-border-width: 2px 2px 2px 2px;"); return;}
		
		try{
			i = Double.parseDouble(interestRate);
			interest.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px;");
		}catch(Exception e){interest.setText("");interest.setStyle("-fx-border-color: red; -fx-border-width: 2px 2px 2px 2px;"); return;}
		
		try{
			t = Double.parseDouble(time);
			years.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px;");
		}catch(Exception e){years.setText("");years.setStyle("-fx-border-color: red; -fx-border-width: 2px 2px 2px 2px;"); return;}
		
		payment payment = new payment();
		String payments = payment.loan(p,i,t);
		resultPayments.setText(payments);
		resultInterest.setText(payment.totalInterest);
		resultTotal.setText(payment.totalPaid);
		
		history.appendText("Principle: $" + principle.getText() + "\n");
		history.appendText("Interest: " + interest.getText() + "%\n");
		history.appendText("Term: " + years.getText() +" years\n");
		history.appendText("Monthly Payments: $" + payments + "\n");
		history.appendText("Total Interest: $" + payment.totalInterest + "\n");
		history.appendText("Total Payments: $" + payment.totalPaid + "\n");
		history.appendText("--------------------------------------------");
		history.appendText("\n");
	}
	
	public void clearAll(){
		principle.setText("");
		interest.setText("");
		years.setText("");
		resultPayments.setText("");
		resultInterest.setText("");
		resultTotal.setText("");
	}
	
	public void clear(){
		history.clear();
		history.appendText("                             History\n");
	}
}
