package metadata.csaude.server

import grails.gorm.transactions.Transactional
import mz.org.csaude.metadata.protection.Role
import mz.org.csaude.metadata.protection.SecUser
import mz.org.csaude.metadata.protection.UserRole
import mz.org.csaude.metadata.server.Server

import java.util.stream.Collectors

class BootStrap {

    def init = { servletContext ->

        Server.withTransaction { initServers() }

        Role.withTransaction {
            addInitialUsers()
        }

     //   initiateDrugsList()
     //   initiateRegimensList()
    }

    @Transactional
    void addInitialUsers(){

        def adminRole = Role.findByAuthorityIlike("%ROLE_ADMIN%")


        if (!adminRole) {
            adminRole = new Role('ROLE_ADMIN').save(flush: true, failOnError: true)
            adminRole.save(flush: true, failOnError: true)
        }

      def user = new SecUser('admin', 'admin').save()
        UserRole.create(user, adminRole, true)
    }


    void initServers() {
        for (serverObject in listServers()) {
            if (!Server.findByCodeAndDestination(serverObject.code, serverObject.destination)) {
                Server server = new Server()
                server.id = serverObject.id
                server.urlPath = serverObject.urlPath
                server.port = serverObject.port
                server.destination = serverObject.destination
                server.code = serverObject.code
                server.type = serverObject.type
                server.save(flush: true, failOnError: true)
            }
        }
    }

    List<Object> listServers() {
        List<Object> provincialServerList = new ArrayList<>()
        provincialServerList.add(new LinkedHashMap(id: '7036157a-61c3-4515-9ab8-fc68359d9402', code: '13', urlPath: 'http://10.1.1.13:', port: '8099', destination: 'CENTRAL_TOOL', type: 'PROD'))
        provincialServerList.add(new LinkedHashMap(id: '257016e7-628d-4d72-8b50-c16c32380767', code: '99', urlPath: 'http://10.1.1.13:', port: '8099', destination: 'CENTRAL_TOOL', type: 'TEST'))

        return provincialServerList
    }


    def destroy = {
    }
}
