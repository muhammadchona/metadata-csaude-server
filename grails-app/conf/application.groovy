grails.plugin.springsecurity.password.algorithm = 'bcrypt'
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'

grails.plugin.springsecurity.useSecurityEventListener = true

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'mz.org.csaude.metadata.protection.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'mz.org.csaude.metadata.protection.UserRole'
grails.plugin.springsecurity.authority.className = 'mz.org.csaude.metadata.protection.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/**/**', 			access: ['permitAll']],
	[pattern: '/api/', 				access: ['permitAll']],
	[pattern: '/dbconsole/**',		access: ['permitAll']],
	[pattern: '/plugins/**', 		access: ['permitAll']],
	[pattern: '/secUser/**', 		access: ['permitAll']],
	[pattern: '/user/**', 			access: ['ROLE_ADMIN']],
	[pattern: '/secRole/**', 		access: ['ROLE_ADMIN']],
	[pattern: '/role/**', 			access: ['ROLE_ADMIN']],
	[pattern: '/securityInfo/**', 	access: ['ROLE_ADMIN']],
	[pattern: '/registationCode/**', access: ['ROLE_ADMIN']],
	[pattern: '/api/clients/**', 		access: ['ROLE_ADMIN']],
	[pattern: '/api/product/**', 		access: ['ROLE_ADMIN']],
	[pattern: '/api/pharmaceuticalForm/**', 		access: ['ROLE_ADMIN']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.filterChain.chainMap = [
		[pattern: '/api/**', 	filters:'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter'],
		[pattern: '/**', 		filters:'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']


]
grails.plugin.springsecurity.rest.token.storage.useJwt=true
grails.plugin.springsecurity.rest.token.storage.jwt.useSignedJwt=true
grails.plugin.springsecurity.rest.token.storage.jwt.useEncryptedJwt = true
grails.plugin.springsecurity.rest.token.storage.jwt.secret = 'edK2k1P0D4770W56B6Rckf1TErImPWcu'

grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
 grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'

grails.plugin.springsecurity.rest.token.rendering.usernamePropertyName='id'

grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'

grails.plugin.springsecurity.logout.postOnly = false

grails.plugin.springsecurity.rejectIfNoRule = false

grails.plugins.springsecurity.password.bcrypt.logrounds = 12

