import mz.org.csaude.metadata.protection.*
// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    corsFilterTest(CorsFilter)
    accessTokenJsonRenderer(CustomAppRestAuthTokenJsonRenderer)
    customerSecurityEventListener(CustomSecurityEventListener)
}
