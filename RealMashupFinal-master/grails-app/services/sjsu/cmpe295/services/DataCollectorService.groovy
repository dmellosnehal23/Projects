
package sjsu.cmpe295.services

import com.xlson.groovycsv.CsvParser

class DataCollectorService {
	def zillowService
	def dataNormalizerService
	def rupdateService = new RupdateService()

	def getAddressesFromCsv() {
		def noOfRecordsInserted = 0
		def noOfZillowCalls = 0
		def csvIterator
		def csvFileObject = new File( "E:\\CMPE295B\\data\\SanJose2.csv" ).withReader {
			csvIterator = CsvParser.parseCsv( it )
			csvIterator.each {
				def res = zillowService.getSearchResults(it[0], it[1])
				noOfZillowCalls++;
				if(res.erroCode.equalsIgnoreCase("success"))
					noOfRecordsInserted++;
			}
		}

		println("Total number of records inserted: "+ noOfRecordsInserted)
		println("Total number of zillow calls made: "+ noOfZillowCalls)
	}

	def generateNormalizedDataFromResultCsv() {
		def errorMessage
		def noOfRecordsScanned = 0
		def noOfRowsInserted = 0
		def csvIterator

		try{

			def csvFileObject = new File( "E:\\CMPE295B\\results\\soldResult.csv" ).withReader {
				csvIterator = CsvParser.parseCsv( it )
				csvIterator.each {
					String res = dataNormalizerService.normalizeData(it[2].toString(),it[3].toString().toDouble(),
							it[4].toString().toDouble(),it[5].toString(),it[6].toString().toDouble(),it[7].toString(),it[8].toString().toDouble(),
							it[9].toString().toDouble(),it[10].toString().toDouble(),it[11].toString().toDouble(),it[12].toString(),it[13].toString().toDouble(),it[16].toString(),it[17].toString().toDouble(),
							it[18].toString().toDouble(),it[19].toString().toDouble(),it[20].toString().toDouble(),it[21].toString().toDouble(),
							it[22].toString(),it[23].toString().toDouble());
					noOfRecordsScanned++;
					if(res.equalsIgnoreCase("success")) {
						noOfRowsInserted++;
						println("Record no:"+noOfRowsInserted+" inserted")
					}
				}
			}

			println("Total number of records scanned: "+ noOfRecordsScanned)
			println("Total number of records inserted/updated: "+ noOfRowsInserted)
		} catch(Exception e) {
			errorMessage = e.getMessage()
		}
	}
	
	def updateMasterDataFromR() {
		def errorMessage
		def noOfRecordsScanned = 0
		def noOfRowsInserted = 0
		def csvIterator

		try{

			def csvFileObject = new File( "E:\\CMPE295B\\results\\Rresult6.csv" ).withReader {
				csvIterator = CsvParser.parseCsv( it )
				csvIterator.each {
					String res = rupdateService.updateRData(it[0].toInteger());
					noOfRecordsScanned++;
					if(res.equalsIgnoreCase("success")) {
						noOfRowsInserted++;
						println("Record no:"+noOfRowsInserted+" updated")
					}
				}
			}

			println("Total number of records scanned: "+ noOfRecordsScanned)
			println("Total number of records inserted/updated: "+ noOfRowsInserted)
		} catch(Exception e) {
			errorMessage = e.getMessage()
		}
	}
	
	def updatePropertyInfo()
	{	println("In DataCollectorService/updatePropertyInfo")
		
		def errorMessage
		def noOfRecordsScanned = 0
		def noOfRowsUpdated = 0
		def csvIterator

		try{

			def csvFileObject = new File( "E:\\CMPE295B\\results\\Archive 4\\Unsold_dump.csv" ).withReader {
				csvIterator = CsvParser.parseCsv( it )
				csvIterator.each {
					String res = dataNormalizerService.updateData(it[2].toString(),it[32].toString(),it[33].toString());
					noOfRecordsScanned++;
					if(res.equalsIgnoreCase("success")) {
						noOfRowsUpdated++;
						println("Record no:"+noOfRowsUpdated+" updated")
					}
				}
			}

			println("Total number of records scanned: "+ noOfRecordsScanned)
			println("Total number of records updated: "+ noOfRowsUpdated)
		} catch(Exception e) {
			errorMessage = e.getMessage()
		}
	}
	
	def getAccuracyFromRData() {
		def noOfRecordsScanned = 0
		def noOfRecordsAccurate = 0
		List accurateRecords = new ArrayList();
		def csvIterator


			def csvFileObject = new File( "E:\\CMPE295B\\results\\Rresult6.csv" ).withReader {
				csvIterator = CsvParser.parseCsv( it )
				csvIterator.each {
					String res = rupdateService.checkAccuracy(it[0].toInteger());
					noOfRecordsScanned++;
					println("Result: "+res)
					if(res == "true") {
						noOfRecordsAccurate++;
						accurateRecords.add(noOfRecordsScanned)
						println("Record no:"+noOfRecordsScanned+" is accurately estimated")
					}
				}
			}

			println("Total number of records scanned: "+ noOfRecordsScanned)
			println("Total number of records accurate "+ noOfRecordsAccurate)
			println("Accuracy: "+ (noOfRecordsAccurate/noOfRecordsScanned)*100+ "%")
			
			/*
			for (it in accurateRecords )
			{
				println(it)
			}
			*/
	}
}
