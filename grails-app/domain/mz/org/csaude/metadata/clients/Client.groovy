package mz.org.csaude.metadata.clients

class Client {

    String id
    String status
    Date updateDate
    String description
    String code
    String nationalCode
    String name
    String address
    String phone
    String indicator
    String clientTypeCode
    String clientTypeDescription
    String prescriptionLevelCode
    String prescriptionLevelDescription
    String warehouseCode
    String warehouseCodeName
    String unitTypeCode
    String unitTypeDescription
    String provinceCode
    String provinceDescription
    String districtCode
    String districtDescription
    String startDate
    // List<Object> areas

    static mapping = {
        id generator: "assigned"
    }

    static constraints = {
        status nullable: false
        updateDate nullable: false
        code nullable: false
        description nullable: true
        nationalCode nullable: true
        name nullable: true
        address nullable: true //na documentacao vem not null
        phone nullable: true
        indicator nullable: true
        clientTypeCode nullable: true
        clientTypeDescription nullable: true
        prescriptionLevelCode nullable: true
        prescriptionLevelDescription  nullable: true
        warehouseCode nullable: true
        warehouseCodeName nullable: true
        unitTypeCode nullable: true
        unitTypeDescription nullable: true
        provinceCode nullable: true
        provinceDescription  nullable: true
        districtCode  nullable: true
        districtDescription nullable: true
        startDate nullable: true
    }
}
