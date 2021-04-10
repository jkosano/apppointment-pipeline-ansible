node {    

    environment {
        dockerImage = ''
        registry = 'jpk912/appointment2'
        //import docker hub credentials
        //REGISTRY_CREDENTIAL = credentials('DOCKER_ID')
    }
      //def app     
      stage('Clone repository') {               
            
            checkout scm
      }     
      stage('Build image') {    
            sh ''' #!/bin/bash
                echo "Building apache...."
            '''
            website = docker.build("jpk912/appointment", "-f nginx/Dockerfile .")
       }     
      stage('Test image') {           
            sh '''
                echo "Tests would go here dfjiaj...."
            '''  
        }     
       stage('Push image') {

           //push to docker-hub
            docker.withRegistry('https://registry.hub.docker.com', 'DOCKER_ID') {            
                website.push("${env.BUILD_NUMBER}")            
                website.push("latest")        
            }   
            
        }
}