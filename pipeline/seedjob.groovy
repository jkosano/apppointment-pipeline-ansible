
//create a pipeline job
pipelineJob('mypipeline') {
    description('creates a pipeline to allow user to build docker images on commit changes')

    // def projecturl = 'jkosano/apppointment-pipeline-ansible'
    // def repofull = 'https://github.com/jkosano/apppointment-pipeline-ansible'

    def projecturl = 'jkosano/apppointment-pipeline-app'
    def repofull = 'https://github.com/jkosano/apppointment-pipeline-app'

    //configure jenkins job properties
    properties {
        pipelineTriggers {
            triggers {
                githubPush()
            }
        }
    }
    definition {
        //load pipeline script from scm 
        cpsScm {
            // properties {
            //     githubProjectUrl('https://github.com/jkosano/apppointment-pipeline-ansible')
            // }
            scm {
                git {
                    remote {
                        //the github api auto fills with 'https://github.com/ <url-here>'
                        github(projecturl)
                        // credentials()
                        url(repofull)
                    }
                    branches('*/master')
                    browser{}
                }
            }
            //Set the relative location of the pipeline script within the source code repository. Defaults to 'Jenkinsfile'.
            //scriptPath('$PWD/appointment.groovy')
            scriptPath('Jenkinsfile')

            lightweight(true)        
        }
    }
}