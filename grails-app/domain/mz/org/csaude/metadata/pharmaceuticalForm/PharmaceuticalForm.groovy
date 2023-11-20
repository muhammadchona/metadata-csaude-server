package mz.org.csaude.metadata.pharmaceuticalForm

class PharmaceuticalForm {

    String id
    String status
    Date updateDate
    String code
    String description
    String shortDescription

    static mapping = {
        id generator: "assigned"
    }

    static constraints = {
        status nullable: false
        updateDate nullable: false
        code nullable: false
        description nullable: false
        shortDescription nullable: false
    }
}
