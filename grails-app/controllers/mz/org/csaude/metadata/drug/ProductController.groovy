package mz.org.csaude.metadata.drug

import grails.converters.JSON
import grails.validation.ValidationException
import mz.org.csaude.metadata.date.JSONSerializer

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional

@ReadOnly
class ProductController {

    ProductService productService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
          //  respond productService.list(params), model: [productsCount: productService.count()]
        render JSONSerializer.setObjectListJsonResponse(productService.list(params)) as JSON
    }

    def show(Long id) {
        render JSONSerializer.setJsonObjectResponse(productService.get(id)) as JSON
    }

    @Transactional
    def save(Product products) {
        if (products == null) {
            render status: NOT_FOUND
            return
        }
        if (products.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond products.errors
            return
        }

        try {
            productService.save(products)
        } catch (ValidationException e) {
            respond products.errors
            return
        }

        respond products, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Product products) {
        if (products == null) {
            render status: NOT_FOUND
            return
        }
        if (products.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond products.errors
            return
        }

        try {
            productService.save(products)
        } catch (ValidationException e) {
            respond products.errors
            return
        }

        respond products, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Long id) {
        if (id == null || productService.delete(id) == null) {
            render status: NOT_FOUND
            return
        }

        render status: NO_CONTENT
    }
}
