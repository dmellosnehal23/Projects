//##############################################################
//##      @Author: SNEHAL D'MELLO
//##############################################################
package sjsu.cmpe239.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.Stopwords;
import weka.core.converters.ArffLoader;
import weka.core.converters.TextDirectoryLoader;
import weka.core.stemmers.IteratedLovinsStemmer;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.tokenizers.Tokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class TrainModel {
	private TextDirectoryLoader textDirectoryLoader;
	
	public TrainModel()
	{
		System.out.println("In Train Model");
		try{	
			//Reading the arff file
			ArffLoader arffLoader = new ArffLoader();
			arffLoader.setFile(new File("/Users/snehal/Documents/elections/ElectionData.arff"));
			Instances instances = arffLoader.getDataSet(); //will get dataset in arff format
			//System.out.println(instances);
			instances.setClassIndex(1);
			
			//Tokenizer
			Tokenizer nGramTokenizer = new NGramTokenizer();
			
			//Stemmer
			IteratedLovinsStemmer iLovinsStemmer = new IteratedLovinsStemmer();
			
			StringToWordVector filter = new StringToWordVector();
			filter.setIDFTransform(true);
			filter.setTFTransform(true);
			filter.setAttributeIndices("1");
			//filter.setAttributeNamePrefix(null);
			filter.setDoNotOperateOnPerClassBasis(false);
			filter.setInvertSelection(false);
			filter.setLowerCaseTokens(true);
			filter.setMinTermFreq(1);
			//filter.setNormalizeDocLength(null);		 
			filter.setOutputWordCounts(true);
			filter.setPeriodicPruning(-1.0);
			filter.setStemmer(iLovinsStemmer);
			//filter.setStopwords(stopWordsFile);		
			//filter.stopwordsTipText();
			filter.setTokenizer(nGramTokenizer);
			filter.setUseStoplist(true);
			filter.setWordsToKeep(100);	
			
			filter.setInputFormat(instances);
			//Filter.runFilter(filter,null);
			Instances filteredData = Filter.useFilter(instances, filter);
			System.out.println(filteredData.firstInstance());

			//Instances filteredData = filter.getOutputFormat();

			//System.out.println(filteredData);	
			
			//Building a classifier
			Classifier naiveBayes = (Classifier)new NaiveBayes();
			naiveBayes.buildClassifier(filteredData);
			
			Evaluation evalNBayes = new Evaluation(filteredData);
			//evalNBayes.evaluateModel(naiveBayes, filteredData);
			
			//System.out.println(evalNBayes.toSummaryString());
			
			Classifier j48 = (Classifier)new J48();
			j48.buildClassifier(filteredData);
			
			Evaluation evalJ48 = new Evaluation(filteredData);
			//evalJ48.evaluateModel(j48, filteredData);
			evalJ48.crossValidateModel(j48, filteredData, 10, new Random(1));
			System.out.println(evalJ48.toSummaryString());			
		}
		catch(IOException e){
			System.out.println(e.getMessage());		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}	
	}	
	public void train()
	{
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrainModel tm = new TrainModel();
		//tm.train();
	}

}
