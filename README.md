## "Grails 3.x Rest API Security with Spring Security"
### Version : 
  
    | Grails Version: 3.2.6
    | Groovy Version: 2.4.7
    | JVM Version: 1.8.0_181

### Step 1 : Create the applicatin : 

    grails create-app myApp --profile rest-api

### setp 2 : Run the application : 

    grails run-app 

### Step 3 : Create the domain class 

    grails create-domain-class Product

### Step 4 : Add the column or variable name in domain class product 
    like - 
    
          String name
          Double price
          String companyName
          String description
          Date dateCreated = new Date()

### Step 5 : Go to BootStrap.groovy and insert some data : 
 

    if (Product.count() == 0){
            new Product(name: "product1", price: 10, companyName: "company1", description:"description1").save(flush:true)
            new Product(name: "product2", price: 100, companyName: "company2", description:"description2").save(flush:true)
            new Product(name: "product3", price: 1000, companyName: "company3", description:"description3").save(flush:true)
            new Product(name: "product4", price: 10000, companyName: "company4", description:"description4").save(flush:true)
        }


### Step 6 : Create restful controller : 

    grails create-restful-controller Product


### Step 7 : Add this dependency : 

    compile 'org.grails.plugins:spring-security-core:3.2.0'

#### Note - if above dependency will not work then add this dependency : 


    compile "org.grails.plugins:spring-security-core:3.1.1"


### Step 8 : Compile application 

    grails compile 

### Step 9 : create the user and role by using - 

    grails s2-quickstart spring.security.rest User Role

### Step 10 : Paste this code to the bootStrap.grovy below the if(product.count()==0){new ............} 

    def role1 = new Role(authority:"ROLE_USER").save flush:true
        def user1 = new User(username:"user@gmail.com",password:"pwd@123").save flush:true
        UserRole.create(user1,role1)


### Step 11 : add another dependency : 

    compile "org.grails.plugins:spring-security-rest:2.0.0.M2"

### Step 12 : again compile the application by using 

    grails compile 

### Step 13 : Go to application.groovy and add chainMap configuration:

  
    grails.plugin.springsecurity.filterChain. chainMap = [
    [pattern: '/**',filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter'],
    [pattern: '/**', filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'] 
    ]

### Step 14 : The final configuration look like : 


    // Added by the Spring Security Core plugin:
    grails.plugin.springsecurity.userLookup.userDomainClassName = 'spring.security.rest.User'
    grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'spring.security.rest.UserRole'
    grails.plugin.springsecurity.authority.className = 'spring.security.rest.Role'
    grails.plugin.springsecurity.controllerAnnotations.staticRules = [
     [pattern: '/',               access: ['permitAll']],
     [pattern: '/error',          access: ['permitAll']],
     [pattern: '/index',          access: ['permitAll']],
     [pattern: '/index.gsp',      access: ['permitAll']],
     [pattern: '/shutdown',       access: ['permitAll']],
     [pattern: '/assets/**',      access: ['permitAll']],
     [pattern: '/**/js/**',       access: ['permitAll']],
     [pattern: '/**/css/**',      access: ['permitAll']],
     [pattern: '/**/images/**',   access: ['permitAll']],
     [pattern: '/**/favicon.ico', access: ['permitAll']],
     [pattern: '/**',             access: ['isFullyAuthenticated()']]
    ]
     
    grails.plugin.springsecurity.filterChain. chainMap = [
      [pattern: '/**',filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter'],
      [pattern: '/**', filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'] 
    ]
 


## Dependency used : 

    //spring security core dependency
    compile "org.grails.plugins:spring-security-core:3.1.1"
    compile "org.grails.plugins:spring-security-rest:2.0.0.M2"



Here, for authorization key use "Bearer access_token". You can see data as shown above. Because in our application.groovy we configure in such a way that all the login users can access data.

    [pattern: '/**',             access: ['isFullyAuthenticated()']]


##### Ref : 
   https://www.360learntocode.com/2018/03/securing-grails-application-with-spring.html

   https://www.djamware.com/post/58a53b5180aca748640ce350/securing-rest-api-with-grails-3-and-spring-security-rest

