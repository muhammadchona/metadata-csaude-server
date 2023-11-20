package mz.org.csaude.metadata.clients

import grails.gorm.services.Service

@Service(Client)
interface ClientsService {

    Client get(Serializable id)

    List<Client> list(Map args)

    Long count()

    Client delete(Serializable id)

    Client save(Client clients)

}
