package mz.org.csaude.metadata.server

class Server {

    String id
    String code
    String urlPath
    String port
    String destination
    String type

    static mapping = {
        id generator: "assigned"

    }

    static constraints = {
        urlPath(nullable: false, blank: false)
        code(nullable: false, maxSize: 50, blank: false,unique: ['destination'])
        port(nullable: true)
        type(nullable: false,blank: false)
        destination(nullable: false,blank: false)
    }


}
