import java.io.File;


public class ProgressService {
	
	public double checkMultipleRun(ProcessInfo processInfo)
	{
		double totalFileNum = processInfo.totalSubNumber*processInfo.numOfRuns;
		double fileNumCount = 0.0;
		for(int s=1;s<=processInfo.totalSubNumber;s++)
		{
			if (processInfo.multiRunOutPutDir != null && processInfo.multiRunOutPutDir.trim().length() > 1) {
				File dir = new File(processInfo.multiRunOutPutDir+"/"+s+"/");
				String[] files = dir.list();
				for (int f = 0; f < files.length; f++) {
					String filename = files[f];
					if ( filename.endsWith("_D.txt"))
						fileNumCount++;
				} // for
			} // if
		}
		return (fileNumCount/totalFileNum)*100;
	}

}
