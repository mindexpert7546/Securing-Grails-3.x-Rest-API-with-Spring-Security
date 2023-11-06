// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'spring.security.rest.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'spring.security.rest.UserRole'
grails.plugin.springsecurity.authority.className = 'spring.security.rest.Role'
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
 [pattern: '/**',             access: ['isFullyAuthenticated()']]
]
 
grails.plugin.springsecurity.filterChain. chainMap = [
  [pattern: '/**',filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter'],
  [pattern: '/**', filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'] 
]
 