package mz.org.csaude.metadata.clients

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class ClientsController {

    ClientsService clientsService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond clientsService.list(params), model:[clientsCount: clientsService.count()]
    }

    def show(Long id) {
        respond clientsService.get(id)
    }

    @Transactional
    def save(Client clients) {
        if (clients == null) {
            render status: NOT_FOUND
            return
        }
        if (clients.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clients.errors
            return
        }

        try {
            clientsService.save(clients)
        } catch (ValidationException e) {
            respond clients.errors
            return
        }

        respond clients, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Client clients) {
        if (clients == null) {
            render status: NOT_FOUND
            return
        }
        if (clients.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clients.errors
            return
        }

        try {
            clientsService.save(clients)
        } catch (ValidationException e) {
            respond clients.errors
            return
        }

        respond clients, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || clientsService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
