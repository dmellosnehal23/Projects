//##############################################################
//##      @Author: SNEHAL D'MELLO
//##############################################################
package sjsu.cmpe239.processor;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import sjsu.cmpe239.util.DBWorker;
import sjsu.cmpe239.util.TwitterOAuth;
import sjsu.cmpe239.util.Utilities;
import weka.classifiers.Classifier;
import weka.classifiers.UpdateableClassifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.SparseInstance;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.stemmers.IteratedLovinsStemmer;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.tokenizers.Tokenizer;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class ElectionAnalysis {
	
	private Classifier classifier = null;
	private Instances trainingInstances = null;
	//Instances filteredData;
	private Evaluation baseModelEval = null;
	private TwitterOAuth oAuth = null;
	private String modelSaveFile = null;
	private String testFile = null;
	//private Map<String, Integer> result;
	private Long countRepublican = 0L;
	private Long countDemocrat = 0L;
	private DBWorker dbConn = null;
	private Thread dbWorker = null;
	
	public ElectionAnalysis(String fileName) throws Exception {
		try{
			ArffReader arffReader = new ArffReader(new FileReader(fileName));
			this.trainingInstances = arffReader.getData(); //will get dataset in arff format
			this.trainingInstances.setClassIndex(1);
			
			if (!this.trainingInstances.equalHeaders(Utilities.getBaseStructure())) 
				  throw new IllegalStateException("Incompatible training set!");
		
			//Initialize Classifier
			this.classifier = this.getClassifierInstance(null);
		} catch(IOException e){
			this.trainingInstances = null;
					
			//Initialize Classifier with model 
			this.classifier = this.getClassifierInstance(fileName);
		}

		this.dbConn = new DBWorker(); 
		this.dbWorker = new Thread(dbConn);
		this.dbWorker.start();
	}
	
	private Classifier getClassifierInstance(String modelLoadFile) throws Exception{
		System.out.println("Initializing Classifier...");
		
		
		if(modelLoadFile != null) {
			return (Classifier) SerializationHelper.read(modelLoadFile);
		} else {
			//Classifier c = new J48();
			Classifier c = new NaiveBayes();
			
			Filter [] fs = new Filter[2];
			fs[0] = this.createStringToWordVector();
			fs[1] = new AttributeSelection();
			
			MultiFilter mf = new MultiFilter();
			mf.setFilters(fs);
			
			FilteredClassifier fClassifier = new FilteredClassifier();
		
			fClassifier.setClassifier(c);
			fClassifier.setFilter(mf);
			
			return fClassifier;
		}
	}
	
	private StringToWordVector createStringToWordVector() throws Exception{
		try{
			//Tokenizer
			Tokenizer nGramTokenizer = new NGramTokenizer();	
			//Stemmer
			IteratedLovinsStemmer iLovinsStemmer = new IteratedLovinsStemmer();
			
			StringToWordVector strfilter = new StringToWordVector();
			strfilter.setIDFTransform(true);
			strfilter.setTFTransform(true);
			strfilter.setAttributeIndices("1");
			//strfilter.setAttributeNamePrefix(null);
			strfilter.setDoNotOperateOnPerClassBasis(false);
			strfilter.setInvertSelection(false);
			strfilter.setLowerCaseTokens(true);
			strfilter.setMinTermFreq(5);
			//strfilter.setNormalizeDocLength(null);		 
			strfilter.setOutputWordCounts(true);
			strfilter.setPeriodicPruning(-1.0);
			strfilter.setStemmer(iLovinsStemmer);
			//strfilter.setStopwords(stopWordsFile);		
			//strfilter.stopwordsTipText();
			strfilter.setTokenizer(nGramTokenizer);
			strfilter.setUseStoplist(true);
			strfilter.setWordsToKeep(1000);	
		
			//strfilter.runFilter(strfilter,null);		
			return strfilter;
		} catch(Exception e){
			System.out.println(e);
			throw new Exception("Internal Error in createStringToWordVectorFilter");
		}
	}
	
	private void buildModel(){
		try{
			System.out.println("Building Model...");
			if(this.trainingInstances != null) {
				
				Utilities.preProcessTweetText(this.trainingInstances);
				
				//System.out.println(this.trainingInstances.firstInstance());
				this.classifier.buildClassifier(this.trainingInstances);
				this.saveModel();
				
				baseModelEval = new Evaluation(this.trainingInstances);
				baseModelEval.crossValidateModel(this.classifier, this.trainingInstances, 10, new Random(1));
				System.out.println(baseModelEval.toSummaryString()); // Print the o/p i.e accuracy eg: correctly classified instances, incorrectly classified instances
			}
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	private void testModel(){
		System.out.println("Testing Model...");
		try{
			int count = 0;
			while(true){
				count++;
				if(count >= 180){
					Thread.sleep(10000);
					count = 0;
				}	
				
				Instances testingInstances = null;	
				if(this.testFile != null)
					testingInstances = Utilities.getArffTestData(this.testFile);
				else 
					testingInstances = Utilities.getTestData();
					//testingInstances = this.getTwitterData();
				
				if(testingInstances == null) break;
				
				//System.out.println(this.trainingInstances);
				//System.out.println(testingInstances);
				if (this.trainingInstances != null && !this.trainingInstances.equalHeaders(testingInstances)) 
					  throw new IllegalStateException("Incompatible train and test set!");
				
				Utilities.preProcessTweetText(testingInstances);
				
				Iterator<Instance> iterateTestData =  testingInstances.iterator();
				while(iterateTestData.hasNext()){
					double classifiedIndex = this.classifier.classifyInstance(iterateTestData.next());
					String result = testingInstances.attribute(1).value((int)classifiedIndex); // refer to the code in getTwitterData()/getTestData() to see the hierarchy of labels added to attributes and attributes aded to instances.
					
					if("republican".equals(result))
						this.countRepublican++;
					else if("democrat".equals(result))
						this.countDemocrat++;

					//System.out.println(testingInstances.attribute(1).value((int)classifiedIndex));
				}	
				
				//System.out.println("countRepublican : " + this.countRepublican + "\ncountDemocrat : " + this.countDemocrat);
				this.dbConn.updateCount(this.countRepublican, this.countDemocrat);
				this.updateModel(testingInstances);
			}
		} catch(Exception e){
			System.out.println(e);
		}
		//this.dbWorker.interrupt();
	}
	
	private void updateModel(Instances testInstances){
		if(this.trainingInstances == null) return;
		
		System.out.println("Updating Model...");
		try{
			int idx = this.trainingInstances.numInstances();
			System.out.println("IDX1 - " + this.trainingInstances.numInstances());
			
			System.out.println("Adding " + testInstances.numInstances() + " Instances to training set");
			this.trainingInstances.addAll(testInstances);
			
			System.out.println("IDX2 - " + this.trainingInstances.numInstances());
			FilteredClassifier testClassifier = (FilteredClassifier)FilteredClassifier.makeCopy(this.classifier);
			
			Evaluation testModelEval = new Evaluation(this.trainingInstances);
			
			testModelEval.crossValidateModel(testClassifier, this.trainingInstances, 10, new Random(1));
			System.out.println(testModelEval.toSummaryString());
			
			System.out.println(this.baseModelEval.errorRate() + " >= " + testModelEval.errorRate());
			if(this.baseModelEval.errorRate() >= testModelEval.errorRate()){
				System.out.println("Old error rate:"+this.baseModelEval.errorRate());
				this.classifier = testClassifier;
				this.baseModelEval = testModelEval;
				System.out.println("New error rate:"+this.baseModelEval.errorRate());
				
				//save new model
				this.buildModel();
			}
			else{
				System.out.println("Removing " + testInstances.numInstances() + " Instances from training set");
				
				for(int i = this.trainingInstances.numInstances(); i > idx; i--)
					this.trainingInstances.remove(i-1);
			}
			
			System.out.println("IDX3 - " + this.trainingInstances.numInstances());
		}catch(Exception e){
			System.out.println(e);
		}	
	}
	
	public void setModelSaveFile(String file){
		this.modelSaveFile = file;
	}
	
	public void setTestFile(String file) {
		this.testFile = file;
	}
	
	private void saveModel(){
		try{
			if(this.modelSaveFile == null)
			{	
				this.modelSaveFile = System.getProperty("user.dir") + "/" + this.classifier.getClass().getSimpleName() + ".model";
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.modelSaveFile));
			oos.writeObject(this.classifier);
			oos.flush();
			oos.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
	private Instances getTwitterData(){
		try {
			
			Instances testingInstances = Utilities.getBaseStructure();
			
			OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/search/tweets.json?q=obama OR mccain");
			this.oAuth.signRequest(request);
			Response response = request.send();
			
			if(response != null && response.isSuccessful()){
				//System.out.println(response.getBody());
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject)parser.parse(response.getBody());
				JSONArray statuses = (JSONArray)jsonObj.get("statuses");
				
				Iterator<JSONObject> it = statuses.iterator();
				while(it.hasNext()){
					JSONObject tweetData = it.next();
					tweetData.get("metadata");
					String tweet = (String)tweetData.get("text");
					//System.out.println(tweet);
					
					double [] data = new double[testingInstances.numAttributes()];
					Instance instance = new SparseInstance(1.0, data);
					testingInstances.add(instance);
				}
			}
			return testingInstances;
		} catch(Exception e) {
			System.out.println(e);
		}	
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Run Configuration -> arguments -> VM arguments : -Xms512M -Xmx2048M
		try{
			ElectionAnalysis ea = new ElectionAnalysis("/Users/snehal/Documents/elections/Classified/trainingData.arff");
			//ea.setTestFile("/Users/snehal/Documents/elections/TestData/testDemocrat_2class.arff");
			ea.setTestFile("/Users/snehal/Documents/elections/TestData/testDemocrat.arff");
			//ea.setTestFile("/Users/snehal/Documents/elections/TestData/testRepublican.arff");
			ea.buildModel();
			ea.testModel();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
