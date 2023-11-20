package mz.org.csaude.metadata.protection


import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer
import groovy.json.JsonBuilder
import org.springframework.security.core.GrantedAuthority

import javax.transaction.Transactional

/**
 * Created by Prakash Thete on 17/04/2018
 */
class CustomAppRestAuthTokenJsonRenderer implements AccessTokenJsonRenderer  {

//    @Override
    @Transactional
    String generateJson(AccessToken accessToken){
        def mainEntityAssociated = ''
        def secUser = null
        def source = ''
        def rolesMenus = new ArrayList()
        def clinicSectorUsers = new HashSet()





        // Add extra custom parameters if you want in this map to be rendered in login response
        Map response = [
                id           : accessToken.principal.id,
                username     : accessToken.principal.username,
                access_token : accessToken.accessToken,
                token_type   : "Bearer",
                refresh_token: accessToken.refreshToken,
                role        : accessToken.authorities.collect { GrantedAuthority role -> role.authority },
                mainEntity   : mainEntityAssociated,
                source       : source,

        ]

        return new JsonBuilder( response ).toPrettyString()
    }
}
