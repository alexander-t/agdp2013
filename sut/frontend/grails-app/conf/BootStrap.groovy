import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
        if (GrailsUtil.developmentEnv) {
        }
    }
    def destroy = {
    }
}
