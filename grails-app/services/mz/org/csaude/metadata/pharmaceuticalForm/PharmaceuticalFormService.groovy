package mz.org.csaude.metadata.pharmaceuticalForm

import grails.gorm.services.Service

@Service(PharmaceuticalForm)
interface PharmaceuticalFormService {

    PharmaceuticalForm get(Serializable id)

    List<PharmaceuticalForm> list(Map args)

    Long count()

    PharmaceuticalForm delete(Serializable id)

    PharmaceuticalForm save(PharmaceuticalForm pharmaceuticalForm)

}
