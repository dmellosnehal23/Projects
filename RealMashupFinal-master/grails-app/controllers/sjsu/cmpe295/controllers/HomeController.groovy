package sjsu.cmpe295.controllers

import java.text.DecimalFormat
import sjsu.cmpe295.models.MasterUnSoldProperty
import sjsu.cmpe295.models.User

class HomeController {
	def dataQueryService
	
	def index() {
		println("In class HomeController/index()")
		render(view: "index")
	}
	
	def admin() {
		println("In class HomeController/admin()")
		render(view: "admin")
	}

	def listings()
	{
		println("In class HomeController/listings()")
		render(view: "listings")
	}

    def showResult()
    {
	   println("In class HomeController/showResult()")
	   render(view:"result")
    }
	
    def showError()
    {
	   println("In class HomeController/showError()")
	   render(view:"error")
    }
	
	def show404()
	{
	   println("In class HomeController/showError()")
	   render(view:"404")
	}

}

