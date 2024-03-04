package metadata.csaude.server

class UrlMappings {

    static mappings = {
        get "/api/$controller(.$format)?"(action:"index")
        get "/api/$controller/$id(.$format)?"(action:"show")
        get "/api/product/getProductsByAreas(.$format)(.$format)"(controller:'product', action:'getProductsByAreas')
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
