package sjsu.cmpe295.controllers



class DataPopulaterController {

	def dataCollectorService


	def index() {
		println("In class DataPopulaterController/index()")
		//redirect(action : 'home')
		render(view: "home")
		println("In class DataPopulaterController/last index()")

	}


	def home() {
		//construct home page
		def errorMessage
		println("In class DataPopulaterController/home()")
		try{
			dataCollectorService.getAddressesFromCsv();
		} catch(Exception e) {
			errorMessage = e.getMessage()
		}
	}

	def details(){
		//@TODO: fetch property details and pass to the view
	}
}
