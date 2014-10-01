/*
 dataSource {
	 pooled = true
	 driverClassName = "org.h2.Driver"
	 username = "sa"
	 password = ""
 }
 hibernate {
	 cache.use_second_level_cache = true
	 cache.use_query_cache = false
	 cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
 //    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
 }
 
 // environment specific settings
 environments {
	 development {
		 dataSource {
			 dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			 url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		 }
	 }
	 test {
		 dataSource {
			 dbCreate = "update"
			 url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		 }
	 }
	 production {
		 dataSource {
			 dbCreate = "update"
			 url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			 properties {
				maxActive = -1
				minEvictableIdleTimeMillis=1800000
				timeBetweenEvictionRunsMillis=1800000
				numTestsPerEvictionRun=3
				testOnBorrow=true
				testWhileIdle=true
				testOnReturn=false
				validationQuery="SELECT 1"
				jdbcInterceptors="ConnectionState"
			 }
		 }
	 }
 }
 */
 /*
  //H2
  dataSource {
	  pooled = true
	  driverClassName = "org.h2.Driver"
	  username = "sa"
	  password = ""
  }
  hibernate {
	  cache.use_second_level_cache = true
	  cache.use_query_cache = false
	  cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
  }
  // environment specific settings
  environments {
	  development {
		  dataSource {
			  dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			  url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		  }
	  }
	  test {
		  dataSource {
			  dbCreate = "update"
			  url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		  }
	  }
	  production {
		  dataSource {
			  dbCreate = "update"
			  url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			  pooled = true
			  properties {
				 maxActive = -1
				 minEvictableIdleTimeMillis=1800000
				 timeBetweenEvictionRunsMillis=1800000
				 numTestsPerEvictionRun=3
				 testOnBorrow=true
				 testWhileIdle=true
				 testOnReturn=true
				 validationQuery="SELECT 1"
			  }
		  }
	  }
  }
  */
  
  /*
   
  // mongo
  grails {
	  mongo {
		host = "localhost"
		port = 27107
		username = "root"
		password=""
		databaseName = "properties"
	  }
	}
  */
  
  //mysql
  
  dataSource {
	  pooled = true
	  driverClassName = "com.mysql.jdbc.Driver"
	  username = "root"
	  password = "root"
  }
  hibernate {
	  cache.use_second_level_cache = true
	  cache.use_query_cache = true
	  cache.provider_class = "net.sf.ehcache.hibernate.EhCacheProvider"
  }
  
  // environment specific settings
  environments {
		development {
			  dataSource {
					dbCreate = "update" // one of 'create', 'create-drop','update'
					//url = "jdbc:mysql://localhost:3306/GrailsGumball_development"
					//url = "jdbc:mysql://localhost:3306/cmpe295B"
					url = "jdbc:mysql://127.0.0.1:3306/cmpe295B"
			  }
		}
		test {
			  dataSource {
					dbCreate = "update"
					url = "jdbc:mysql://localhost:3306/GrailsGumball_test"
			  }
		}
		/**
		production {
			  dataSource {
					dbCreate = "update"
					url = "jdbc:mysql://localhost:3306/GrailsGumballAppFog"
			  }
		}
		**/
		production {
			def envVar = System.env.VCAP_SERVICES
			def credentials = envVar?grails.converters.JSON.parse(envVar)["mysql-5.1"][0]["credentials"]:null
		 
			dataSource {
			   pooled = true
			   dbCreate = "update"
			   driverClassName = "com.mysql.jdbc.Driver"
			   url =  credentials?"jdbc:mysql://${credentials.hostname}:${credentials.port}/${credentials.name}?useUnicode=yes&characterEncoding=UTF-8":""
			   username = credentials?credentials.username:""
			   password = credentails?credentials.password:""
			}
		 }
		
  }
 