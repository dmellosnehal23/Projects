class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/home/index")
		"500"(view:'/error')
		
		"/rest/$sku?"(controller: "rest", parseRequest: true) {
			action = [GET: "getProperties"]
		}
		
		"/rest/neighborhood/$sku?"(controller: "rest", parseRequest: true) {
			action = [GET: "getNeighborhoods"]
		}
		
		"/rest/ajax/$sku?"(controller: "rest", parseRequest: true) {
			action = [GET: "getPropertiesInfoByAjax"]
		}
		
		"/rest/user/$sku?"(controller: "rest", parseRequest: true) {
			action = [POST: "authenticateUser", PUT: "registerUser"]
		}
		
		"/rest/watchlist/$sku?"(controller: "rest", parseRequest: true) {
			action = [POST: "addToUserWatchlist", PUT: "removeFromWatchList", GET: "getUserWatchlist" ]
		}
	
		name register: "/register"(view:"/home/register")
		name logout: "/logout"(view:"/home/logout")
	}
}
