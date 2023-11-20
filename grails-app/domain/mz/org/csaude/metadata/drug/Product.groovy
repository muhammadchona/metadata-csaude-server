package mz.org.csaude.metadata.drug

import mz.org.csaude.metadata.therapeuticRegimen.Regimen

class Product {

    String id // sameIdmed
    String status
    Date updateDate
    String fnm
    String description
    String shortDescription
    String fullDescription
    boolean prioritary
    boolean kit
    String temperatureCode
    String temperatureDescription
    String categoryCode
    String categoryDescription
    String prescriptionLevelCode
    String prescriptionLevelDescription
    String pharmaceuticFormCode
    String pharmaceuticFormDescription
    String therapeuticClasseCode
    String therapeuticClasseDescription
    String dosageCode
    String dosageDescription
    String productUnitCode
    String productUnitDescription
    String classificationCode
    String classificationDescription
    String monthlyConsumption
    String volume
    String unitsPerPack
    String managedByPack
    String uuidOpenmrs
    // List<Object> areas
    static belongsTo = [Regimen]
    static hasMany = [ therapeuticRegimenList: Regimen]


    static mapping = {
        id generator: "assigned"
    }

    static constraints = {
        status nullable: false
        updateDate nullable: false
        fnm nullable: false
        description nullable: true
        shortDescription nullable: true
        fullDescription nullable: true
        prioritary nullable: false
        kit nullable: false
        temperatureCode nullable: true
        temperatureDescription nullable: true
        categoryCode nullable: true
        categoryDescription nullable: true
        prescriptionLevelCode nullable: true
        prescriptionLevelDescription  nullable: true
        pharmaceuticFormCode nullable: true
        pharmaceuticFormDescription nullable: true
        therapeuticClasseCode nullable: true
        therapeuticClasseDescription  nullable: true
        dosageCode  nullable: true
        dosageDescription nullable: true
        productUnitCode nullable: true
        productUnitDescription nullable: true
        classificationCode nullable: true
        classificationDescription nullable: false
        monthlyConsumption nullable: true
        volume  nullable: true
        unitsPerPack  nullable: true
        managedByPack  nullable: true
        uuidOpenmrs nullable: true
    }
}
