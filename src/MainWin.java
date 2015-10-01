import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class MainWin {

	protected Shell shell;
	public ProcessInfo processInfo = new ProcessInfo();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWin window = new MainWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		ProgressService progressService = new ProgressService();
		//
		shell = new Shell();
		shell.setSize(822, 562);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		// ***********************************************************************************************
		// *********************************** Tab-SetUp
		// ***********************************************************************************************
		TabItem tbSetUp = new TabItem(tabFolder, SWT.NONE);
		tbSetUp.setText("Set Up");

		// ***************************** composite_SetUp
		// *****************************
		Composite composite_SetUp = new Composite(tabFolder, SWT.NONE);
		composite_SetUp.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tbSetUp.setControl(composite_SetUp);
		composite_SetUp.setLayout(null);

		// ********* composite_SetUp_Data *********
		Composite composite_SetUp_Data = new Composite(composite_SetUp,
				SWT.BORDER);
		composite_SetUp_Data.setToolTipText("11");
		composite_SetUp_Data.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_SetUp_Data.setBounds(30, 38, 739, 90);
		// -------------------------------------------------------------
		Label lblLoadOriginalData = new Label(composite_SetUp_Data, SWT.NONE);
		lblLoadOriginalData.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblLoadOriginalData.setBounds(265, 0, 159, 22);
		lblLoadOriginalData.setFont(SWTResourceManager.getFont(
				"Times New Roman", 15, SWT.NORMAL));
		lblLoadOriginalData.setText("Load Original Data");
		// -------------------------------------------------------------
		Label lblDirectoryOfInput = new Label(composite_SetUp_Data, SWT.NONE);
		lblDirectoryOfInput.setText("Directory of input: ");
		lblDirectoryOfInput.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lblDirectoryOfInput.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lblDirectoryOfInput.setBounds(53, 64, 672, 22);
		// -------------------------------------------------------------
		Label lbNumberOfSub = new Label(composite_SetUp_Data, SWT.NONE);
		lbNumberOfSub.setBounds(559, 2, 166, 22);
		lbNumberOfSub.setText("Number of Subjects: ");
		lbNumberOfSub.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbNumberOfSub.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		// -------------------------------------------------------------
		Button btnInput = new Button(composite_SetUp_Data, SWT.CENTER);
		// Click button of load data
		btnInput.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
				dialog.setFilterPath("/ifs/loni/faculty/thompson/four_d/dzhu");
				String inputDir = dialog.open();
				// Count subject number
				// if close window without choosing a directory
				if (inputDir != null && inputDir.trim().length() > 1) {
					processInfo.multiRun_OriDataDir = inputDir;
					File dir = new File(processInfo.multiRun_OriDataDir);
					String[] files = dir.list();
					for (int f = 0; f < files.length; f++) {
						String filename = files[f];
						if (filename.startsWith("sub")
								&& filename.endsWith(".txt"))
							processInfo.multiRun_TotalSubNumber++;
					} // for
				} // if
				lblDirectoryOfInput.setText("Directory of input: "
						+ processInfo.multiRun_OriDataDir);
				lbNumberOfSub.setText("Number of Subjects: "
						+ processInfo.multiRun_TotalSubNumber);
			}
		});
		btnInput.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		btnInput.setBounds(288, 28, 105, 25);
		btnInput.setText("Input Data");

		// ***************************** composite_SetUp_MultiRun
		// *****************************
		Composite composite_SetUp_MultiRun = new Composite(composite_SetUp,
				SWT.BORDER);
		composite_SetUp_MultiRun.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite_SetUp_MultiRun.setBounds(30, 150, 739, 337);

		// -------------------------------------------------------------
		Label lblEstimateGroupError = new Label(composite_SetUp_MultiRun,
				SWT.NONE);
		lblEstimateGroupError.setBounds(185, 0, 347, 22);
		lblEstimateGroupError
				.setText("Estimate Group Error  of Sparse Learning");
		lblEstimateGroupError.setFont(SWTResourceManager.getFont(
				"Times New Roman", 15, SWT.NORMAL));
		lblEstimateGroupError.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		// -------------------------------------------------------------
		Label lbMultiRunOutput = new Label(composite_SetUp_MultiRun, SWT.NONE);
		lbMultiRunOutput.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		lbMultiRunOutput.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbMultiRunOutput.setBounds(316, 62, 409, 22);
		// -------------------------------------------------------------
		Spinner spNumOfRun = new Spinner(composite_SetUp_MultiRun, SWT.BORDER);
		spNumOfRun.setMinimum(1);
		spNumOfRun.setSelection(100);
		spNumOfRun.setBounds(130, 62, 56, 22);
		// -------------------------------------------------------------
		Label lbNumOfRuns = new Label(composite_SetUp_MultiRun, SWT.NONE);
		lbNumOfRuns.setText("Number of runs: ");
		lbNumOfRuns.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbNumOfRuns.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbNumOfRuns.setBounds(22, 62, 102, 22);
		// -------------------------------------------------------------
		Button btnMultiRunOutput = new Button(composite_SetUp_MultiRun,
				SWT.NONE);
		// Click button of multiRunDir
		btnMultiRunOutput.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
				dialog.setFilterPath("/ifs/loni/faculty/thompson/four_d/dzhu");
				String multiRunOutput = dialog.open();
				if (multiRunOutput != null
						&& multiRunOutput.trim().length() > 1) {
					processInfo.multiRun_OutPutDir = multiRunOutput;
				} // if
				lbMultiRunOutput.setText(processInfo.multiRun_OutPutDir);
			}
		});
		btnMultiRunOutput.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		btnMultiRunOutput.setBounds(192, 59, 118, 25);
		btnMultiRunOutput.setText("MultiRun Output");
		// -------------------------------------------------------------
		Label lbMultiRunDicSize = new Label(composite_SetUp_MultiRun, SWT.NONE);
		lbMultiRunDicSize.setText("Diction Size:");
		lbMultiRunDicSize.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		lbMultiRunDicSize.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbMultiRunDicSize.setBounds(134, 145, 81, 22);
		// -------------------------------------------------------------
		Spinner spMultiRunDicSize = new Spinner(composite_SetUp_MultiRun,
				SWT.BORDER);
		spMultiRunDicSize.setMaximum(500);
		spMultiRunDicSize.setMinimum(100);
		spMultiRunDicSize.setSelection(400);
		spMultiRunDicSize.setBounds(247, 145, 56, 22);
		// -------------------------------------------------------------
		Label lbMultiRunSampleEleNumber = new Label(composite_SetUp_MultiRun,
				SWT.NONE);
		lbMultiRunSampleEleNumber.setText("SampleEle Number:");
		lbMultiRunSampleEleNumber.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbMultiRunSampleEleNumber.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbMultiRunSampleEleNumber.setBounds(385, 145, 129, 22);
		// -------------------------------------------------------------
		Spinner spMultiRunSampleEleNumber = new Spinner(
				composite_SetUp_MultiRun, SWT.BORDER);
		spMultiRunSampleEleNumber.setMaximum(500);
		spMultiRunSampleEleNumber.setMinimum(100);
		spMultiRunSampleEleNumber.setSelection(284);
		spMultiRunSampleEleNumber.setBounds(520, 145, 56, 22);
		// -------------------------------------------------------------
		Label lbMultiRunEpochNum = new Label(composite_SetUp_MultiRun, SWT.NONE);
		lbMultiRunEpochNum.setText("Epoch Number:");
		lbMultiRunEpochNum.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbMultiRunEpochNum.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbMultiRunEpochNum.setBounds(134, 210, 102, 22);
		// -------------------------------------------------------------
		Spinner spMultiRunEpochNumber = new Spinner(composite_SetUp_MultiRun,
				SWT.BORDER);
		spMultiRunEpochNumber.setDigits(1);
		spMultiRunEpochNumber.setMinimum(1);
		spMultiRunEpochNumber.setSelection(1);
		spMultiRunEpochNumber.setBounds(247, 210, 81, 22);
		// -------------------------------------------------------------
		Label lbMultiRunLambda = new Label(composite_SetUp_MultiRun, SWT.NONE);
		lbMultiRunLambda.setText("Lambda:");
		lbMultiRunLambda.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		lbMultiRunLambda.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbMultiRunLambda.setBounds(385, 210, 102, 22);
		// -------------------------------------------------------------
		Spinner spMultiRunLambda = new Spinner(composite_SetUp_MultiRun,
				SWT.BORDER);
		spMultiRunLambda.setMinimum(1);
		spMultiRunLambda.setSelection(8);
		spMultiRunLambda.setDigits(2);
		spMultiRunLambda.setBounds(518, 210, 81, 22);
		// -------------------------------------------------------------
		Label lbMultiRunEstimationProgress = new Label(
				composite_SetUp_MultiRun, SWT.NONE);
		lbMultiRunEstimationProgress.setText("Progress");
		lbMultiRunEstimationProgress.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbMultiRunEstimationProgress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbMultiRunEstimationProgress.setBounds(455, 271, 232, 22);
		// -------------------------------------------------------------
		Button btnMultiRun = new Button(composite_SetUp_MultiRun, SWT.NONE);
		btnMultiRun.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// set disable after click
				btnMultiRun.setEnabled(false);
				btnMultiRun.redraw();
				btnMultiRun.update();
				// save paramaters
				processInfo.multiRun_NumOfRuns = Integer.parseInt(spNumOfRun
						.getText());// spNumOfRun.getSelection();
				processInfo.multiRun_DicSize = Integer
						.parseInt(spMultiRunDicSize.getText());// spDicSize.getSelection();
				processInfo.multiRun_SampleEleNum = Integer
						.parseInt(spMultiRunSampleEleNumber.getText());// spSampleEleNumber.getSelection();
				processInfo.multiRun_EpochNum = Double
						.parseDouble(spMultiRunEpochNumber.getText());// spEpochNumber.getSelection();
				processInfo.multiRun_Lambda = Double
						.parseDouble(spMultiRunLambda.getText());// spLambda.getSelection();

				// run MutipleRun.sh
				Process proc = null;
				try {
					proc = Runtime.getRuntime().exec(
							"sh /ifshome/dzhu/scripts/Journal_ESL/codeDemo/MultipleRun.sh "
									+ processInfo.multiRun_OriDataDir + " "
									+ processInfo.multiRun_TotalSubNumber + " "
									+ processInfo.multiRun_OutPutDir + " "
									+ processInfo.multiRun_NumOfRuns + " "
									+ processInfo.multiRun_DicSize + " "
									+ processInfo.multiRun_SampleEleNum + " "
									+ processInfo.multiRun_EpochNum + " "
									+ processInfo.multiRun_Lambda);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					proc.waitFor();
					BufferedReader stdInput = new BufferedReader(
							new InputStreamReader(proc.getInputStream()));
					String stdOutput = null;
					while ((stdOutput = stdInput.readLine()) != null) {
						System.out.println(stdOutput);
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// checkProgress
				int checkProgress = 0;
				do {
					try {
						Thread.sleep(6000); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					checkProgress = (int) progressService
							.checkMultipleRun(processInfo);
					System.out.println("Check multipleRun progress..."
							+ checkProgress);
					lbMultiRunEstimationProgress
							.setText("Paralleled sparse learning: "
									+ checkProgress + "% ...");
					lbMultiRunEstimationProgress.redraw();
					lbMultiRunEstimationProgress.update();
				} while (checkProgress != 100.0);
				// Calculate the error limit
				lbMultiRunEstimationProgress
						.setText("Integrating parallel results ...");
				lbMultiRunEstimationProgress.redraw();
				lbMultiRunEstimationProgress.update();
				progressService.calculateErrorLimit(processInfo);
				lbMultiRunEstimationProgress
						.setText("Integrating parallel results finished!");
				lbMultiRunEstimationProgress.redraw();
				lbMultiRunEstimationProgress.update();
				// set enalbe if estimation is fished
				btnMultiRun.setEnabled(true);
			}
		});
		btnMultiRun.setText("Estimate Learning Error");
		btnMultiRun.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		btnMultiRun.setBounds(251, 271, 180, 25);

		// ***********************************************************************************************
		// *********************************** Tab-ESL
		// ***********************************************************************************************
		TabItem tabEsl = new TabItem(tabFolder, SWT.NONE);
		tabEsl.setText("ESL");

		// ***************************** composite_ESL
		// *****************************
		Composite composite_ESL = new Composite(tabFolder, SWT.NONE);
		composite_ESL.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		tabEsl.setControl(composite_ESL);
		Composite composite_ESL_step1 = new Composite(composite_ESL, SWT.BORDER);
		composite_ESL_step1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		Composite composite_ESL_step2 = new Composite(composite_ESL, SWT.BORDER);
		composite_ESL_step2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		Button btnDoStep1 = new Button(composite_ESL_step1, SWT.NONE);
		Button btnDoStep2 = new Button(composite_ESL_step2, SWT.NONE);
		// -------------------------------------------------------------
		Label lbStartSub = new Label(composite_ESL, SWT.NONE);
		lbStartSub.setBounds(64, 23, 56, 22);
		lbStartSub.setText("SubStart:");
		lbStartSub.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbStartSub.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		// -------------------------------------------------------------
		Spinner spSubStartID = new Spinner(composite_ESL, SWT.BORDER);
		spSubStartID.setBounds(126, 23, 56, 22);
		spSubStartID.setMaximum(67);
		spSubStartID.setMinimum(1);
		spSubStartID.setSelection(1);
		// -------------------------------------------------------------
		Label lbEndSub = new Label(composite_ESL, SWT.NONE);
		lbEndSub.setText("SubEnd:");
		lbEndSub.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbEndSub.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbEndSub.setBounds(188, 23, 56, 22);
		// -------------------------------------------------------------
		Spinner spSubEndID = new Spinner(composite_ESL, SWT.BORDER);
		spSubEndID.setMaximum(68);
		spSubEndID.setMinimum(2);
		spSubEndID.setSelection(68);
		spSubEndID.setBounds(250, 23, 56, 22);
		// -------------------------------------------------------------
		Label lbESLOutput = new Label(composite_ESL, SWT.NONE);
		lbESLOutput.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbESLOutput.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESLOutput.setBounds(449, 23, 349, 22);
		// -------------------------------------------------------------
		Button btnESLOutput = new Button(composite_ESL, SWT.NONE);
		btnESLOutput.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shell, SWT.OPEN);
				dialog.setFilterPath("/ifs/loni/faculty/thompson/four_d/dzhu");
				String ESLOutput = dialog.open();
				if (ESLOutput != null && ESLOutput.trim().length() > 1) {
					processInfo.ESL_OutPutDir = ESLOutput;
				} // if
				lbESLOutput.setText(processInfo.ESL_OutPutDir);
			}
		});
		btnESLOutput.setText("ESL Output");
		btnESLOutput.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		btnESLOutput.setBounds(325, 20, 118, 25);

		// ********* composite_ESL_step1 *********
		composite_ESL_step1.setBounds(64, 63, 419, 176);
		// -------------------------------------------------------------
		Label lbStep1 = new Label(composite_ESL_step1, SWT.NONE);
		lbStep1.setText("STEP - 1");
		lbStep1.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbStep1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbStep1.setBounds(179, 4, 60, 22);
		// -------------------------------------------------------------
		Label lbPValueThresLB = new Label(composite_ESL_step1, SWT.NONE);
		lbPValueThresLB.setText("P-value LB:");
		lbPValueThresLB.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		lbPValueThresLB.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbPValueThresLB.setBounds(24, 32, 81, 22);
		// -------------------------------------------------------------
		Spinner spPValueThresLB = new Spinner(composite_ESL_step1, SWT.BORDER);
		spPValueThresLB.setMinimum(1);
		spPValueThresLB.setSelection(50);
		spPValueThresLB.setDigits(2);
		spPValueThresLB.setBounds(117, 32, 60, 22);
		// -------------------------------------------------------------
		Label lbPValueThresUB = new Label(composite_ESL_step1, SWT.NONE);
		lbPValueThresUB.setText("P-value UB:");
		lbPValueThresUB.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		lbPValueThresUB.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbPValueThresUB.setBounds(212, 32, 81, 22);
		// -------------------------------------------------------------
		Spinner spPValueThresUB = new Spinner(composite_ESL_step1, SWT.BORDER);
		spPValueThresUB.setIncrement(5);
		spPValueThresUB.setMinimum(1);
		spPValueThresUB.setSelection(60);
		spPValueThresUB.setDigits(2);
		spPValueThresUB.setBounds(332, 32, 60, 22);
		// -------------------------------------------------------------
		Label lbPValueStep = new Label(composite_ESL_step1, SWT.NONE);
		lbPValueStep.setText("P-value Step:");
		lbPValueStep.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbPValueStep.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbPValueStep.setBounds(24, 72, 81, 22);
		// -------------------------------------------------------------
		Spinner spPValueStep = new Spinner(composite_ESL_step1, SWT.BORDER);
		spPValueStep.setMaximum(10);
		spPValueStep.setMinimum(1);
		spPValueStep.setSelection(1);
		spPValueStep.setDigits(2);
		spPValueStep.setBounds(117, 72, 60, 22);
		// -------------------------------------------------------------
		Label lbPValueSimilarThre = new Label(composite_ESL_step1, SWT.NONE);
		lbPValueSimilarThre.setText("Similarity Thre:");
		lbPValueSimilarThre.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbPValueSimilarThre.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbPValueSimilarThre.setBounds(212, 72, 99, 22);
		// -------------------------------------------------------------
		Spinner spPValueSimilarThre = new Spinner(composite_ESL_step1,
				SWT.BORDER);
		spPValueSimilarThre.setMinimum(1);
		spPValueSimilarThre.setSelection(50);
		spPValueSimilarThre.setDigits(2);
		spPValueSimilarThre.setBounds(332, 72, 60, 22);
		// -------------------------------------------------------------
		Label lbVoteRate = new Label(composite_ESL_step1, SWT.NONE);
		lbVoteRate.setText("Vote Rate:");
		lbVoteRate.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbVoteRate.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbVoteRate.setBounds(24, 117, 81, 22);
		// -------------------------------------------------------------
		Spinner spVoteRate = new Spinner(composite_ESL_step1, SWT.BORDER);
		spVoteRate.setMinimum(1);
		spVoteRate.setSelection(50);
		spVoteRate.setDigits(2);
		spVoteRate.setBounds(117, 117, 60, 22);
		// -------------------------------------------------------------
		Label lbVRPValueThre = new Label(composite_ESL_step1, SWT.NONE);
		lbVRPValueThre.setText("P-value VR:");
		lbVRPValueThre.setFont(SWTResourceManager.getFont("Times New Roman",
				12, SWT.NORMAL));
		lbVRPValueThre.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbVRPValueThre.setBounds(212, 117, 81, 22);
		// -------------------------------------------------------------
		Spinner spVRPvalueThre = new Spinner(composite_ESL_step1, SWT.BORDER);
		spVRPvalueThre.setMinimum(1);
		spVRPvalueThre.setSelection(50);
		spVRPvalueThre.setDigits(2);
		spVRPvalueThre.setBounds(332, 117, 60, 22);
		// -------------------------------------------------------------
		Label lbESL_Step1_Progress = new Label(composite_ESL_step1, SWT.NONE);
		Label lbESL_Step2_Progress = new Label(composite_ESL_step2, SWT.NONE);
		lbESL_Step1_Progress.setText("Progress");
		lbESL_Step1_Progress.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbESL_Step1_Progress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESL_Step1_Progress.setBounds(148, 148, 232, 22);
		// -------------------------------------------------------------
		btnDoStep1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProgressService progressService = new ProgressService();
				// set disable after click
				btnDoStep1.setEnabled(false);
				btnDoStep1.redraw();
				btnDoStep1.update();
				btnDoStep2.setEnabled(false);
				btnDoStep2.redraw();
				btnDoStep2.update();
				lbESL_Step2_Progress.setText("");
				lbESL_Step2_Progress.redraw();
				lbESL_Step2_Progress.update();
				spSubStartID.setEnabled(false);
				spSubEndID.setEnabled(false);
				
				// save paramaters
				processInfo.ESL_SubStartID = Integer.parseInt(spSubStartID
						.getText());
				processInfo.ESL_SubEndID = Integer.parseInt(spSubEndID
						.getText());

				processInfo.ESL_pValueThresholdLB = Double
						.parseDouble(spPValueThresLB.getText());
				processInfo.ESL_pValueThresholdUB = Double
						.parseDouble(spPValueThresUB.getText());
				processInfo.ESL_pValueThresholdStep = Double
						.parseDouble(spPValueStep.getText());
				processInfo.ESL_pValueCheckSimilarThreshold = Double
						.parseDouble(spPValueSimilarThre.getText());
				processInfo.ESL_voteRateThreshold = Double
						.parseDouble(spVoteRate.getText());
				processInfo.ESL_pValueVRThreshold = Double
						.parseDouble(spVRPvalueThre.getText());
				//
				progressService.do_Step1_qsub(processInfo);
				// check progress
				int checkProgress = 0;
				do {
					try {
						Thread.sleep(2000); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					checkProgress = (int) progressService
							.checkStep1_Progress(processInfo);
					System.out.println("Check Step-1 progress..."
							+ checkProgress);
					lbESL_Step1_Progress
							.setText("Paralleled template finding: "
									+ checkProgress + "% ...");
					lbESL_Step1_Progress.redraw();
					lbESL_Step1_Progress.update();
				} while (checkProgress != 100.0);
				// the first time executing this bash script
				if (processInfo.ESL_on_off_firstTime == 1)
					processInfo.ESL_on_off_firstTime = 0;
				// Analyse VR
				lbESL_Step1_Progress.setText("Integrating template info ...");
				lbESL_Step1_Progress.redraw();
				lbESL_Step1_Progress.update();
				ESL_AnalyseVR anaVR = new ESL_AnalyseVR();
				anaVR.optDicIndex = processInfo.ESL_OptDicIndex; // 0-399
				anaVR.dicSize = processInfo.ESL_DicSize; // 400
				anaVR.subStartID = processInfo.ESL_SubStartID; // 1-58?
				anaVR.subEndID = processInfo.ESL_SubEndID; // 10-68
				anaVR.subNum = anaVR.subEndID - anaVR.subStartID + 1;
				anaVR.voteRateThreshold = Double
						.valueOf(processInfo.ESL_voteRateThreshold);
				anaVR.pValueThreshold = Double
						.valueOf(processInfo.ESL_pValueVRThreshold);
				anaVR.ESL_OutPutDir = processInfo.ESL_OutPutDir;
				anaVR.printParaInfo();
				anaVR.initialization();
				boolean foundTemplate = anaVR.findTheTemplate();
				if (foundTemplate) { // do step-2
					lbESL_Step1_Progress.setText("Step-1 finished ...");
					// enalbe step-2
					btnDoStep2.setEnabled(true);
					btnDoStep2.redraw();
					btnDoStep2.update();
				} else
					lbESL_Step1_Progress.setText("No more templates found!");
				lbESL_Step1_Progress.redraw();
				lbESL_Step1_Progress.update();

			}
		});
		btnDoStep1.setText("Do Step 1");
		btnDoStep1.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		btnDoStep1.setBounds(24, 145, 118, 25);

		// ********* composite_ESL_step2 *********
		composite_ESL_step2.setBounds(64, 259, 419, 150);
		// -------------------------------------------------------------
		Label lbStep2 = new Label(composite_ESL_step2, SWT.NONE);
		lbStep2.setText("STEP - 2");
		lbStep2.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbStep2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbStep2.setBounds(186, 10, 60, 22);
		// -------------------------------------------------------------
		Label lbESLDicSize = new Label(composite_ESL_step2, SWT.NONE);
		lbESLDicSize.setText("Diction Size:");
		lbESLDicSize.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbESLDicSize.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESLDicSize.setBounds(31, 38, 81, 22);
		// -------------------------------------------------------------
		Spinner spESLDicSize = new Spinner(composite_ESL_step2, SWT.BORDER);
		spESLDicSize.setMaximum(500);
		spESLDicSize.setMinimum(100);
		spESLDicSize.setSelection(400);
		spESLDicSize.setBounds(133, 38, 56, 22);
		// -------------------------------------------------------------
		Label lbESLSampleEleNumber = new Label(composite_ESL_step2, SWT.NONE);
		lbESLSampleEleNumber.setText("SampleEle Number:");
		lbESLSampleEleNumber.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbESLSampleEleNumber.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESLSampleEleNumber.setBounds(214, 38, 129, 22);
		// -------------------------------------------------------------
		Spinner spESLSampleEleNumber = new Spinner(composite_ESL_step2,
				SWT.BORDER);
		spESLSampleEleNumber.setMaximum(500);
		spESLSampleEleNumber.setMinimum(100);
		spESLSampleEleNumber.setSelection(284);
		spESLSampleEleNumber.setBounds(338, 38, 56, 22);
		// -------------------------------------------------------------
		Label lbESLEpochNum = new Label(composite_ESL_step2, SWT.NONE);
		lbESLEpochNum.setText("Epoch Number:");
		lbESLEpochNum.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbESLEpochNum.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESLEpochNum.setBounds(27, 82, 102, 22);
		// -------------------------------------------------------------
		Spinner spESLEpochNumber = new Spinner(composite_ESL_step2, SWT.BORDER);
		spESLEpochNumber.setMinimum(1);
		spESLEpochNumber.setSelection(1);
		spESLEpochNumber.setDigits(1);
		spESLEpochNumber.setBounds(133, 82, 56, 22);
		// -------------------------------------------------------------
		Label lbESLLambda = new Label(composite_ESL_step2, SWT.NONE);
		lbESLLambda.setText("Lambda:");
		lbESLLambda.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		lbESLLambda.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESLLambda.setBounds(214, 82, 102, 22);
		// -------------------------------------------------------------
		Spinner spESLLambda = new Spinner(composite_ESL_step2, SWT.BORDER);
		spESLLambda.setBounds(334, 82, 60, 22);
		spESLLambda.setMinimum(1);
		spESLLambda.setSelection(8);
		spESLLambda.setDigits(2);
		// -------------------------------------------------------------
		lbESL_Step2_Progress.setText("Progress");
		lbESL_Step2_Progress.setFont(SWTResourceManager.getFont(
				"Times New Roman", 12, SWT.NORMAL));
		lbESL_Step2_Progress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbESL_Step2_Progress.setBounds(151, 113, 232, 22);
		// -------------------------------------------------------------
		btnDoStep2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProgressService progressService = new ProgressService();
				// set disable after click
				btnDoStep2.setEnabled(false);
				btnDoStep2.redraw();
				btnDoStep2.update();
				lbESL_Step1_Progress.setText("");
				lbESL_Step1_Progress.redraw();
				lbESL_Step1_Progress.update();
				// save paramaters
				processInfo.ESL_DicSize = Integer.parseInt(spESLDicSize
						.getText());
				processInfo.ESL_SampleEleNum = Integer
						.parseInt(spESLSampleEleNumber.getText());// spSampleEleNumber.getSelection();
				processInfo.ESL_EpochNum = Double.parseDouble(spESLEpochNumber
						.getText());// spEpochNumber.getSelection();
				processInfo.ESL_Lambda = Double.parseDouble(spESLLambda
						.getText());// spLambda.getSelection();
				//
				progressService.do_Step2(processInfo);
				// check progress
				int checkProgress = 0;
				do {
					try {
						Thread.sleep(10000); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					checkProgress = (int) progressService
							.checkStep2_Progress(processInfo);
					System.out.println("Check Step-2 progress..."
							+ checkProgress);
					lbESL_Step2_Progress.setText("Paralleled optimization: "
							+ checkProgress + "% ...");
					lbESL_Step2_Progress.redraw();
					lbESL_Step2_Progress.update();
				} while (checkProgress != 100.0);
				//
				processInfo.ESL_OptDicIndex++;
				//
				btnDoStep1.setEnabled(true);
				btnDoStep1.redraw();
				btnDoStep1.update();

			}
		});
		btnDoStep2.setText("Do Step 2");
		btnDoStep2.setFont(SWTResourceManager.getFont("Times New Roman", 12,
				SWT.NORMAL));
		btnDoStep2.setBounds(27, 110, 118, 25);
		btnDoStep2.setEnabled(false);
	}
}
