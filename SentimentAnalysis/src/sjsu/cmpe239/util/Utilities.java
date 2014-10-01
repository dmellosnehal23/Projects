//##############################################################

//##      @Author: SNEHAL D'MELLO
//##############################################################
package sjsu.cmpe239.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.ArffSaver;

public abstract class Utilities {

	private static Pattern p1 = Pattern.compile("#([^\\s]+)");
	private static Pattern p2 = Pattern.compile("@[^\\s]+");
	private static Pattern p3 = Pattern.compile("[\\s]+");
	private static Pattern p4 = Pattern.compile("(.)\\1");
	private static Pattern p5 = Pattern.compile("-\\d+(\\.\\d+)?");
	private static Pattern p6 = Pattern.compile("[+]\\d+([.]\\d+)?");
	private static Pattern p7 = Pattern.compile("[^a-zA-Z ]");
	private static Pattern p8 = Pattern.compile("obama|barack|barrack|barack obama");
	private static Pattern p9 = Pattern.compile("john|mccain|mcain|john mccain|john mcain");
	
	public static void preProcessTweetText(Instances tweets){
		
		//System.out.println(tweets);
		//System.out.println(tweets.numInstances());
		//System.out.println(tweets.attribute(0).numValues());
		for(int i= 0; i < tweets.numInstances(); i++){
			Instance tweet = tweets.get(i);
			//System.out.println(i);
			double tweetIdx = tweet.value(0);
			//System.out.println(tweetIdx);
			String tweetText = tweet.attribute(0).value((int)tweetIdx);
			
			//Note: Convert to lowercase
			tweetText = tweetText.trim().toLowerCase();
			
			tweetText = Utilities.p1.matcher(tweetText).replaceAll("$1"); // remove the "#" from beginning of word
			tweetText = Utilities.p2.matcher(tweetText).replaceAll(""); // remove the words having @ as it specifies user name.
			tweetText = Utilities.p3.matcher(tweetText).replaceAll(" "); //replace more than 1 spaces with a space.
			tweetText = Utilities.p4.matcher(tweetText).replaceAll("$1");
			tweetText = Utilities.p5.matcher(tweetText).replaceAll("NEGATIVE");
			tweetText = Utilities.p6.matcher(tweetText).replaceAll("POSITIVE");
			tweetText = Utilities.p7.matcher(tweetText).replaceAll("");
			tweetText = Utilities.p8.matcher(tweetText).replaceAll("democrat_candidate");
			tweetText = Utilities.p9.matcher(tweetText).replaceAll("republican_candidate");

			tweet.setValue(tweet.attribute(0), tweetText);
		}
		
	}
	
	public static Instances getBaseStructure() {
		//1. create list of class lables 1,2,3,4
		List<String> labels = new ArrayList<String>();
		labels.add("democrat");
		labels.add("others");
		labels.add("republican");	
		
		//2. create list of 'Attribute'
		Attribute tweetAttr = new Attribute("text", (List<String>)null);
		Attribute ratingAttr = new Attribute("@@class@@",labels);			
		
		//3. create new Instances and pass relation name and List of Attribute
		List<Attribute> attributes= new ArrayList<Attribute>();
		attributes.add(tweetAttr);
		attributes.add(ratingAttr);
		Instances baseStructure = new Instances("sentimentAnalysis", (ArrayList<Attribute>)attributes, 0);
		//System.out.println(testingInstances);
				
		//4. set class index. Instances.setClassIndex
		baseStructure.setClassIndex(1); //Tell the training data which attribute is to be considered a 'class . '0: index of 'tweet' and 1: index of 'rating'

		return baseStructure;
	}
	
	private static Integer _sIdx = 0;
	private static Integer _eIdx = -1; 
	private static Instances _testInstances = null;
	public static Instances getArffTestData(String fname){
		try {			
			if(Utilities._testInstances == null) {
				ArffReader reader = new ArffReader(new FileReader(fname));
				Utilities._testInstances = reader.getData();
			}
			
			Utilities._sIdx = Utilities._eIdx + 1;
			Utilities._eIdx += 21;
			if(Utilities._eIdx > Utilities._testInstances.numInstances()) Utilities._eIdx = Utilities._testInstances.numInstances();
			
			System.out.println("sIdx " + Utilities._sIdx + " eIdx " + Utilities._eIdx);
			
			Instances results = Utilities._testInstances.stringFreeStructure();
			for(int i = Utilities._sIdx; i < Utilities._eIdx; i++) {
				Instance instance = Utilities._testInstances.get(i);
				results.add(instance);
				double idx = instance.value(0);
				results.instance(results.numInstances() - 1).setValue(0, instance.attribute(0).value((int)idx));
			}
			results.setClassIndex(1);
			//results.addAll(Utilities._testInstance.subList(0, 2));
			
			if(results.numInstances() < 1) return null;
			
			return results;
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public static Instances getTestData(){	
		
		String[] str = new String[4];
		str[0] = "@snehal Future      debate advice for Obama: 1) Fewer words 2) Don't debate details 3) Make McCain laugh 4) More stories  #debate08";
		str[1] = "+1 Obama we need to get rid of programs that don't work and bring in programs that are up to date";
		str[2] = "Mccain +3 for great quote \"It\'s hard to reach across the aisle from that far from the left.\" #tweetdebate";
		str[3] = "+1 McCain Soundbit it's hard to reach across the aisle when you are that far left #tweetdebate";
		
		Instances testingInstances = Utilities.getBaseStructure();
		
		for(int i=0; i<str.length;i++)
		{
			double [] data = new double[testingInstances.numAttributes()];
			data[0] = testingInstances.attribute(0).addStringValue(str[i]);
			Instance tweet = new SparseInstance(1.0, data);
			testingInstances.add(tweet);
		}
		
		return testingInstances;
	}
	
	public static void generateTestData(String txtFile) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(txtFile));
			if(bf != null) {
				String line;
				Instances testingInstances = Utilities.getBaseStructure();
				while((line = bf.readLine()) != null) {
					line = line.trim();
					
					if(line.isEmpty()) continue;
					
					double [] data = new double[testingInstances.numAttributes()];
					data[0] = testingInstances.attribute(0).addStringValue(line);
					Instance tweet = new SparseInstance(1.0, data);
					testingInstances.add(tweet);
				}
				
				if(testingInstances.numInstances() > 0) {
					ArffSaver writer = new ArffSaver();
					writer.setFile(new File(txtFile + ".arff"));
					writer.setInstances(testingInstances);
					writer.writeBatch();
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			//Instances tweets = Utilities.getTestData();
			//ArffReader reader = new ArffReader(new FileReader("/Users/snehal/Documents/elections/Classified/data.arff"));
			//Instances tweets = reader.getData();
			
			//System.out.println(tweets);
			//System.out.println("-----------");
			//Utilities.preProcessTweetText(Utilities.getTestData());
			//Utilities.preProcessTweetText(tweets);
			//System.out.println(tweets);
			
			//System.out.println(Utilities.getArffTestData("/Users/snehal/Documents/elections/snehal.arff").numInstances());
			//System.out.println(Utilities.getArffTestData("/Users/snehal/Documents/elections/snehal.arff").numInstances());
			
			//Utilities.preProcessTweetText(Utilities.getArffTestData("/Users/snehal/Documents/elections/snehal.arff"));
			//Utilities.preProcessTweetText(Utilities.getArffTestData("/Users/snehal/Documents/elections/snehal.arff"));
				
			Utilities.generateTestData("/Users/snehal/Documents/elections/TestData/testing1_moreObamaNegative.txt");
			Utilities.generateTestData("/Users/snehal/Documents/elections/TestData/testing2_moreMccainNegative.txt");
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}

/* References : 
 * http://chaoticity.com/making-a-copy-of-weka-instances/
 */
