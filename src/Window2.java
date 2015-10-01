//import java.io.File;
//
//import org.eclipse.swt.widgets.DirectoryDialog;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.TabFolder;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.TabItem;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.wb.swt.SWTResourceManager;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.widgets.Spinner;
//
//public class Window2 {
//
//	protected Shell shell;
//	public ProcessInfo processInfo = new ProcessInfo();
//
//	/**
//	 * Launch the application.
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		try {
//			Window2 window = new Window2();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Open the window.
//	 */
//	public void open() {
//		Display display = Display.getDefault();
//		createContents();
//		shell.open();
//		shell.layout();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 */
//	protected void createContents() {
//		shell = new Shell();
//		shell.setSize(822, 562);
//		shell.setText("SWT Application");
//		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
//
//		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
//
//		//***********************************************************************************************
//		//*********************************** Tab-SetUp *************************************************
//		//***********************************************************************************************
//		TabItem tbSetUp = new TabItem(tabFolder, SWT.NONE);
//		tbSetUp.setText("Set Up");
//
//		//********* composite_SetUp *********
//		Composite composite_SetUp = new Composite(tabFolder, SWT.NONE);
//		composite_SetUp.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		tbSetUp.setControl(composite_SetUp);
//		composite_SetUp.setLayout(null);
//
//		//********* composite_SetUp_Data *********
//		Composite composite_SetUp_Data = new Composite(composite_SetUp, SWT.BORDER);
//		composite_SetUp_Data.setToolTipText("11");
//		composite_SetUp_Data.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		composite_SetUp_Data.setBounds(30, 38, 739, 90);
//		//-------------------------------------------------------------
//		Label lblLoadOriginalData = new Label(composite_SetUp_Data, SWT.NONE);
//		lblLoadOriginalData.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		lblLoadOriginalData.setBounds(265, 0, 159, 22);
//		lblLoadOriginalData.setFont(SWTResourceManager.getFont(
//				"Times New Roman", 15, SWT.NORMAL));
//		lblLoadOriginalData.setText("Load Original Data");
//		//-------------------------------------------------------------
//		Label lblDirectoryOfInput = new Label(composite_SetUp_Data, SWT.NONE);
//		lblDirectoryOfInput.setText("Directory of input: ");
//		lblDirectoryOfInput.setFont(SWTResourceManager.getFont(
//				"Times New Roman", 12, SWT.NORMAL));
//		lblDirectoryOfInput.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		lblDirectoryOfInput.setBounds(53, 64, 672, 22);
//		//-------------------------------------------------------------
//		Label lbNumberOfSub = new Label(composite_SetUp_Data, SWT.NONE);
//		lbNumberOfSub.setBounds(559, 2, 166, 22);
//		lbNumberOfSub.setText("Number of Subjects: ");
//		lbNumberOfSub.setFont(SWTResourceManager.getFont("Times New Roman", 12,
//				SWT.NORMAL));
//		lbNumberOfSub.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		//-------------------------------------------------------------
//		Button btnInput = new Button(composite_SetUp_Data, SWT.CENTER);
//		// Click button of load data
//		btnInput.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
//				dialog.setFilterPath("c:\\temp");
//				String inputDir = dialog.open();
//				// Count subject number
//				// if close window without choosing a directory
//				if (inputDir != null && inputDir.trim().length() > 1) {
//					processInfo.oriDataDir = inputDir;
//					File dir = new File(processInfo.oriDataDir);
//					String[] files = dir.list();
//					for (int f = 0; f < files.length; f++) {
//						String filename = files[f];
//						if (filename.startsWith("sub")
//								&& filename.endsWith(".txt"))
//							processInfo.totalSubNumber++;
//					} // for
//				} // if
//				lblDirectoryOfInput.setText("Directory of input: " + processInfo.oriDataDir);
//				lbNumberOfSub.setText("Number of Subjects: "
//						+ processInfo.totalSubNumber);
//			}
//		});
//		btnInput.setFont(SWTResourceManager.getFont("Times New Roman", 12,
//				SWT.NORMAL));
//		btnInput.setBounds(288, 28, 105, 25);
//		btnInput.setText("Input Data");
//				
//		//********* composite_SetUp_MultiRun *********
//		Composite composite_SetUp_MultiRun = new Composite(composite_SetUp,
//				SWT.BORDER);
//		composite_SetUp_MultiRun.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		composite_SetUp_MultiRun.setBounds(30, 149, 739, 337);
//
//		//-------------------------------------------------------------
//		Label lblEstimateGroupError = new Label(composite_SetUp_MultiRun,
//				SWT.NONE);
//		lblEstimateGroupError.setBounds(185, 0, 347, 22);
//		lblEstimateGroupError
//				.setText("Estimate Group Error  of Sparse Learning");
//		lblEstimateGroupError.setFont(SWTResourceManager.getFont(
//				"Times New Roman", 15, SWT.NORMAL));
//		lblEstimateGroupError.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		//-------------------------------------------------------------
//		Label lbMultiRunOutput = new Label(composite_SetUp_MultiRun, SWT.NONE);
//		lbMultiRunOutput.setFont(SWTResourceManager.getFont("Times New Roman",
//				12, SWT.NORMAL));
//		lbMultiRunOutput.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		lbMultiRunOutput.setBounds(375, 64, 270, 22);
//		//-------------------------------------------------------------
//		Spinner spNumOfRun = new Spinner(composite_SetUp_MultiRun, SWT.BORDER);
//		spNumOfRun.setMinimum(1);
//		spNumOfRun.setSelection(100);
//		spNumOfRun.setBounds(153, 64, 47, 22);
//		//-------------------------------------------------------------
//		Label lbNumOfRuns = new Label(composite_SetUp_MultiRun, SWT.NONE);
//		lbNumOfRuns.setText("Number of runs: ");
//		lbNumOfRuns.setFont(SWTResourceManager.getFont("Times New Roman", 12,
//				SWT.NORMAL));
//		lbNumOfRuns.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		lbNumOfRuns.setBounds(45, 64, 102, 22);
//		//-------------------------------------------------------------
//		Button btnMultiRunOutput = new Button(composite_SetUp_MultiRun,
//				SWT.NONE);
//		// Click button of multiRunDir
//		btnMultiRunOutput.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
//				dialog.setFilterPath("c:\\temp");
//				String multiRunOutput = dialog.open();
//				if (multiRunOutput != null && multiRunOutput.trim().length() > 1) {
//					processInfo.multiRunOutPutDir = multiRunOutput;
//				} //if
//				lbMultiRunOutput.setText(processInfo.multiRunOutPutDir);
//			}
//		});
//		btnMultiRunOutput.setFont(SWTResourceManager.getFont("Times New Roman",
//				12, SWT.NORMAL));
//		btnMultiRunOutput.setBounds(251, 61, 118, 25);
//		btnMultiRunOutput.setText("MultiRun Output");
//
//		//***********************************************************************************************
//		//*********************************** Tab-ESL ***************************************************
//		//***********************************************************************************************
//		TabItem tabEsl = new TabItem(tabFolder, SWT.NONE);
//		tabEsl.setText("ESL");
//		
//		Composite composite_ESL = new Composite(tabFolder, SWT.NONE);
//		composite_ESL.setBackground(SWTResourceManager
//				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
//		tabEsl.setControl(composite_ESL);
//
//	}
//}
