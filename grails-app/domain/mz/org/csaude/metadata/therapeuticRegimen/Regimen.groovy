package mz.org.csaude.metadata.therapeuticRegimen

import mz.org.csaude.metadata.drug.Product

class Regimen {

    String id
    String status
    Date updateDate
    String code
    String description
    String areaCode
    String areaDescription
    String categoryCode
    String categoryDescription
    String uuidOpenmrs
    static hasMany = [products: Product]

    static mapping = {
        id generator: "assigned"
    }

    static constraints = {
        status nullable: false
        updateDate nullable: false
        code nullable: false
        description nullable: false
        areaCode nullable: false
        areaDescription nullable: false
        categoryCode nullable: true
        categoryDescription nullable: true
        uuidOpenmrs nullable: true
    }
}
