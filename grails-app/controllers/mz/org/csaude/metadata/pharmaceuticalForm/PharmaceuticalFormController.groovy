package mz.org.csaude.metadata.pharmaceuticalForm

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class PharmaceuticalFormController {

    PharmaceuticalFormService pharmaceuticalFormService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pharmaceuticalFormService.list(params), model:[pharmaceuticalFormCount: pharmaceuticalFormService.count()]
    }

    def show(Long id) {
        respond pharmaceuticalFormService.get(id)
    }

    @Transactional
    def save(PharmaceuticalForm pharmaceuticalForm) {
        if (pharmaceuticalForm == null) {
            render status: NOT_FOUND
            return
        }
        if (pharmaceuticalForm.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pharmaceuticalForm.errors
            return
        }

        try {
            pharmaceuticalFormService.save(pharmaceuticalForm)
        } catch (ValidationException e) {
            respond pharmaceuticalForm.errors
            return
        }

        respond pharmaceuticalForm, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(PharmaceuticalForm pharmaceuticalForm) {
        if (pharmaceuticalForm == null) {
            render status: NOT_FOUND
            return
        }
        if (pharmaceuticalForm.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pharmaceuticalForm.errors
            return
        }

        try {
            pharmaceuticalFormService.save(pharmaceuticalForm)
        } catch (ValidationException e) {
            respond pharmaceuticalForm.errors
            return
        }

        respond pharmaceuticalForm, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || pharmaceuticalFormService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
