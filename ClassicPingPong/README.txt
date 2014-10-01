1. Create Project on GCM: 
	Follow step 1 at https://tausiq.wordpress.com/category/android/
	
2. Setup Android Project
	Follow Eclipse->File->Import->Android->"Existing Android Code Into Workspace"
	
3. Install Google Play Services Packages.

 	Download the Google Play services from Android SDK Manager under Extras

	after downloading you will find an Android project at similar to this path:
	android-sdk-macosx_20/extras/google/google_play_services/libproject/google-play-services_lib

	Import this as a library to the android project

	Update "ClassicPingPong" properties:
		Under "Android" section: Add "google-play-services_lib" project imported above. 
	
4. Setup Emulators
	Two instance of Emulators needs to be setup. 
	Use Target Name: "Google API's", Platform: 4.4.2 and API Level: 19
	(Note: Download appropriate API packages via "Android SDK Manager" in Eclipse)
	
5. Obtain Devices Registration Ids
	As backend server was not setup, a small hack has been used to get the game going.
	Load "ClassicPingPong" package on the two emulators one after another. 
		Load on 1st emulator and look at the logs for "CUSTOM_LOG" "Registration Id: <id>"
		Note down <id> i.e "RegId 1"
		Close the App and clear logs.
		Repeat the same for 2nd emulator.
	Copy the two registration Id's into Main Activity (Main.java)
	Public static String [] regIds = {
		"RegId 1",
		"RegId 2"
	}
	
5. Build and Run ClassicPingPong
	Build the ClassicPingPong project again after adding new Registration Id's and Deploy on instances of each emulators created above.
	
6. Play
	Left side is assumed to start 1st. So choose right side 1st and then the left side on the two emulators. 
	
	
	