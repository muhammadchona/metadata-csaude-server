package mz.org.csaude.metadata.clients

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class ClientServiceSpec extends Specification {

    ClientsService clientsService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Client(...).save(flush: true, failOnError: true)
        //new Client(...).save(flush: true, failOnError: true)
        //Client clients = new Client(...).save(flush: true, failOnError: true)
        //new Client(...).save(flush: true, failOnError: true)
        //new Client(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //clients.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        clientsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Client> clientsList = clientsService.list(max: 2, offset: 2)

        then:
        clientsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        clientsService.count() == 5
    }

    void "test delete"() {
        Long clientsId = setupData()

        expect:
        clientsService.count() == 5

        when:
        clientsService.delete(clientsId)
        datastore.currentSession.flush()

        then:
        clientsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Client clients = new Client()
        clientsService.save(clients)

        then:
        clients.id != null
    }
}
