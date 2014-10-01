package sjsu.cmpe295.controllers

import sjsu.cmpe295.services.DataCollectorService


class DataNormalizerController {
	DataCollectorService dataCollectorService = new DataCollectorService();



	def index() {
		println("In class DataNormalizerController/index()")
		redirect(action : 'home')
	}

	def home() {
		//construct home page
		def errorMessage
		println("In class DataNormalizerController/home()")
		
		try{
			dataCollectorService.updatePropertyInfo();
			//render(view:'/index')
			
		} catch(Exception e) {
			errorMessage = e.getMessage()
			render(view:'error')
		}		
	}

	def details(){
		//@TODO: fetch property details and pass to the view
	}
}
