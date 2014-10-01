package sjsu.cmpe295.models

class User {

	String firstname
	String lastname
	String password
	String email
	
	//static belongsTo = Property
	static hasMany = [props : MasterProperty]
	
	static constraints = {
		email(unique: true)
	}
}
