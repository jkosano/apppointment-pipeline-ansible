FROM jenkins/jenkins

#skip jenkins wizard setup
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

#copy plugins.txt file and run install-plugins.sh to install plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# copy user info setup for jenkins
# COPY casc.yml /var/jenkins_home/casc.yml 
# ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yml
COPY casc.yml /usr/local/casc.yml 
ENV CASC_JENKINS_CONFIG /usr/local/casc.yml

#copy jenkinsfile for jenkins job configured as code
COPY seedjobtest.groovy /usr/local/seedjobtest.groovy
COPY seedjob.groovy /usr/local/seedjob.groovy

#install ansible
USER root
RUN apt-get update && apt-get -y install ansible

# Install Docker - docker uses debian
RUN apt-get update && \
apt-get -y install apt-transport-https \
     ca-certificates \
     curl \
     gnupg2 \
     software-properties-common && \
curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey; apt-key add /tmp/dkey && \
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
   $(lsb_release -cs) \
   stable" && \
apt-get update && \
apt-get -y install docker-ce

# COmpose

RUN curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && chmod +x /usr/local/bin/docker-compose

RUN usermod -aG docker jenkins

USER jenkins

