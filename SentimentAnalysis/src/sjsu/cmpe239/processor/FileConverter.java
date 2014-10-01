//##############################################################
//##      @Author: SNEHAL D'MELLO
//##############################################################
package sjsu.cmpe239.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Stopwords;
import weka.core.converters.ArffSaver;
import weka.core.converters.TextDirectoryLoader;

public class FileConverter {

	private void convert(){
		try{
			//create base structure
			//1. create list of class lables 1,2,3,4
			List<String> labels = new ArrayList<String>();
			labels.add("negative");
			labels.add("positive");
			labels.add("mixed");
			labels.add("other");	
			
			//2. create list of 'Attribute'
			Attribute tweetAttr = new Attribute("tweet", (List<String>)null);
			Attribute ratingAttr = new Attribute("rating",labels);			
			
			//3. create new Instances and pass relation name and List of Attribute
			List<Attribute> attributes= new ArrayList<Attribute>();
			attributes.add(tweetAttr);
			attributes.add(ratingAttr);
			Instances trainingData = new Instances("sentimentAnalysis", (ArrayList<Attribute>)attributes, 0);
			System.out.println(trainingData);
					
			//4. set class index. Instances.setClassIndex
			trainingData.setClassIndex(1); //Tell the training data which attribute is to be considered a 'class . '0: index of 'tweet' and 1: index of 'rating'
 			
			BufferedReader reader = new BufferedReader(new FileReader("/Users/snehal/Documents/elections/debate08_sentiment_tweets.tsv"));
			
			String line;
			while((line = reader.readLine()) != null){
				String [] tweetSplit = line.split("\t");
				
				
				//check if tweet length < 2 then continue;
				//System.out.println("Before If");
				if(tweetSplit.length < 6 || tweetSplit[5].isEmpty())
					continue;
				
				double [] data = new double[trainingData.numAttributes()];
				//adding tweetSplit[2] to the attr(0) of the training data. AND storing it in data[0] as a double value as data is stored in WEKA as double value.
				data[0] = trainingData.attribute(0).addStringValue(tweetSplit[2]);
				data[1] = new Double(tweetSplit[5]) - 1; //Convert String to Double. mapping since our labels have index of 1 less than these.
				Instance tweet = new DenseInstance(1.0, data);
				
				trainingData.add(tweet);				
				
				//create a single Instanc				
				//add Instance to Instances
			}
			//System.out.println(trainingData);
			reader.close();
			
			//save Instances to o/p file using ArffSaver
			if(!trainingData.isEmpty()){
				ArffSaver arffSaver = new ArffSaver();
				arffSaver.setFile(new File("/Users/snehal/Documents/elections/ElectionData.arff"));
				System.out.println("Hello");
				arffSaver.setInstances(trainingData);
				arffSaver.writeBatch();
				
				ArffSaver arffSaver2 = new ArffSaver();
				arffSaver2.setFile(new File("/Users/snehal/Documents/elections/ElectionData2.arff"));
				System.out.println("Hello");
				arffSaver2.setInstances(trainingData);
				arffSaver2.writeBatch();
			}		
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void foo(){
		//this.textDirectoryLoader =  new TextDirectoryLoader();
		//this.textDirectoryLoader.setDirectory(new File("/Users/snehal/Documents/elections/"));
		
		//FileWriter fileWriter = new FileWriter("/Users/snehal/Documents/elections/data.arff");
		//fileWriter.write(this.textDirectoryLoader.getDataSet().toString());
		//fileWriter.close();
		
		//StopWords
		Stopwords stopwords = new Stopwords();
		File stopWordsFile = new File("/Users/snehal/Documents/elections/StopWords.txt");
		//stopwords.write(stopWordsFile);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileConverter fileConverter = new FileConverter();
		fileConverter.convert();
	}
}
