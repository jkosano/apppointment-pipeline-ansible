
//create a pipeline job
pipelineJob('mypipeline') {
    description('creates a pipeline to allow user to build docker images on commit changes')

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
            scm {
                git {
                    remote {
                        github('jpk912/appointment-pipeline.git')
                        credentials()
                        //repository url
                        url()
                    }
                    branches{
                        '*/master'
                    }
                }
            }
            //Set the relative location of the pipeline script within the source code repository. Defaults to 'Jenkinsfile'.
            //scriptPath('$PWD/appointment.groovy')
            scriptPath('roles/jenkins/files/jenkins-pipeline/pipeline/Jenkinsfile')

            lightweight(boolean lightweight = true)        }

        //
        configure {
            it / 'triggers' << 'com.cloudbees.jenkins.GitHubPushTrigger'{
                spec''
            }
            scm('')
                        }
        }

}