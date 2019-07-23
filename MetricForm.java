import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.wb.swt.SWTResourceManager;

public class MetricForm extends Dialog{

	protected Object result;
	protected Shell shlMetric;
	private Text txtName;
	private Text txtHeight;
	private Text txtWeight;

	/**
	 * Launch the application.
	 * @param parent
	 * @param style
	 */
	
	public MetricForm(Shell parent, int style) {
		super(parent, style);
		setText("BMI Calculator");
	}

	/**
	 * Open the window.
	 */
	
	public Object open() {
		createContents();
		shlMetric.open();
		shlMetric.layout();
		Display display = getParent().getDisplay();
		while (!shlMetric.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlMetric = new Shell();
		shlMetric.setSize(477, 300);
		shlMetric.setText("Metric Entry");
		
		Label lblName = new Label(shlMetric, SWT.NONE);
		lblName.setBounds(76, 23, 61, 25);
		lblName.setText("Name: ");
		
		txtName = new Text(shlMetric, SWT.BORDER);
		txtName.setBounds(243, 20, 157, 31);
		
		Label lblHeightIn = new Label(shlMetric, SWT.NONE);
		lblHeightIn.setText("Height in centimeters: ");
		lblHeightIn.setBounds(22, 77, 185, 25);
		
		txtHeight = new Text(shlMetric, SWT.BORDER);
		txtHeight.setBounds(243, 74, 157, 31);
		
		Button btnReturn = new Button(shlMetric, SWT.NONE);
		btnReturn.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlMetric.close();
			}
		});
		btnReturn.setBounds(289, 188, 105, 35);
		btnReturn.setText("Return");
		
		Button btnSubmit = new Button(shlMetric, SWT.NONE);
		btnSubmit.setForeground(SWTResourceManager.getColor(153, 102, 255));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int option = 1;
					MainForm.bmiList.add(new BMI(txtName.getText().toLowerCase(), Integer.parseInt(txtWeight.getText()), 
							Integer.parseInt(txtHeight.getText()), option));
				}
				catch(Exception e1){
					MessageBox messageBox = new MessageBox(shlMetric, 0);
				    messageBox.setText("Metric Entry Error");
				    messageBox.setMessage("Invalid Metric Entry, values were defaulted");
				    messageBox.open();
				    int option = 1;
				    MainForm.bmiList.add(new BMI("Unknown", 70, 170, option));
				}
			}
		});
		btnSubmit.setBounds(33, 188, 105, 35);
		btnSubmit.setText("Submit");
		
		Label lblWeightInKilograms = new Label(shlMetric, SWT.NONE);
		lblWeightInKilograms.setText("Weight in kilograms: ");
		lblWeightInKilograms.setBounds(32, 133, 172, 25);
		
		txtWeight = new Text(shlMetric, SWT.BORDER);
		txtWeight.setBounds(243, 130, 157, 31);
		shlMetric.setTabList(new Control[]{txtName, txtHeight, txtWeight, btnSubmit, btnReturn});
	}
}
