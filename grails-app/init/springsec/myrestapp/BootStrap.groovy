package springsec.myrestapp
import spring.security.rest.User
import spring.security.rest.UserRole
import spring.security.rest.Role
class BootStrap {

    def init = { servletContext ->

    if (Product.count() == 0){
            new Product(name: "product1", price: 10, companyName: "company1", description:"description1").save(flush:true)
            new Product(name: "product2", price: 100, companyName: "company2", description:"description2").save(flush:true)
            new Product(name: "product3", price: 1000, companyName: "company3", description:"description3").save(flush:true)
            new Product(name: "product4", price: 10000, companyName: "company4", description:"description4").save(flush:true)
        }


        def role1 = new Role(authority:"ROLE_USER").save flush:true
        def user1 = new User(username:"admin",password:"admin").save flush:true
        UserRole.create(user1,role1)
    }
    def destroy = {
    }
}
