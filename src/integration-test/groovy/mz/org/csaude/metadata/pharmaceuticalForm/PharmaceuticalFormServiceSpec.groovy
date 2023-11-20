package mz.org.csaude.metadata.pharmaceuticalForm

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.grails.datastore.mapping.core.Datastore
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class PharmaceuticalFormServiceSpec extends Specification {

    PharmaceuticalFormService pharmaceuticalFormService
    @Autowired Datastore datastore

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PharmaceuticalForm(...).save(flush: true, failOnError: true)
        //new PharmaceuticalForm(...).save(flush: true, failOnError: true)
        //PharmaceuticalForm pharmaceuticalForm = new PharmaceuticalForm(...).save(flush: true, failOnError: true)
        //new PharmaceuticalForm(...).save(flush: true, failOnError: true)
        //new PharmaceuticalForm(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //pharmaceuticalForm.id
    }

    void cleanup() {
        assert false, "TODO: Provide a cleanup implementation if using MongoDB"
    }

    void "test get"() {
        setupData()

        expect:
        pharmaceuticalFormService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PharmaceuticalForm> pharmaceuticalFormList = pharmaceuticalFormService.list(max: 2, offset: 2)

        then:
        pharmaceuticalFormList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pharmaceuticalFormService.count() == 5
    }

    void "test delete"() {
        Long pharmaceuticalFormId = setupData()

        expect:
        pharmaceuticalFormService.count() == 5

        when:
        pharmaceuticalFormService.delete(pharmaceuticalFormId)
        datastore.currentSession.flush()

        then:
        pharmaceuticalFormService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PharmaceuticalForm pharmaceuticalForm = new PharmaceuticalForm()
        pharmaceuticalFormService.save(pharmaceuticalForm)

        then:
        pharmaceuticalForm.id != null
    }
}
